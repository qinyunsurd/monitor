package com.surd.monitor.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.NewService;
import org.springframework.beans.factory.annotation.Value;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

/**
 * @Author: GuLang
 * @Date: Create in 15:24 2019-08-06
 * @Description:
 * @Version: 1.0
 */
public class ConsulAutoIServiceRegistration implements IServiceRegistry {

    private final ConsulClient client;

    private NewService newService;
    public static final int DEFAULT_PORT = 8080;
    @Value("${server.port:#{null }}")
    private Integer serverPort;
    @Value("${management.server.port:#{null }}")
    private Integer managementServerPort;
    @Value("${spring.application.name}")
    private String appName;
    @Value("${management.server.servlet.context-path:}")
    private String contextPath;
    @Value("${toona.metrics.registry.service-check-interval:1m}")
    private String serviceCheckInterval;

    public ConsulAutoIServiceRegistration(MetricsRegistryProperties metricsRegistryProperties) {
        this.client = new ConsulClient(metricsRegistryProperties.getHost(),metricsRegistryProperties.getPort());
    }

    private boolean registered;

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public void destroy(){
        stop();
    }

    private void stop() {
        //判断是否已经注册
        if (null != newService){
            close();
        }
    }

    @Override
    public void registry() throws Exception {
        if (isRegistered()) {
            return;
        }
        if (null == newService) {
            newService = new NewService();
        }
        newService.setName(appName);
        newService.setTags(Collections.singletonList("metrics"));
        String ip = this.getIp();
        newService.setAddress(ip);
        int port = this.getPort();
        newService.setPort(port);
        newService.setId(appName + "_" + ip + "_" + port);

        NewService.Check serviceCheck = new NewService.Check();
        serviceCheck.setDeregisterCriticalServiceAfter("10m");
        serviceCheck.setHttp("http://" + ip + ":" + port + contextPath +"/actuator/health");
        serviceCheck.setInterval(serviceCheckInterval);
        newService.setCheck(serviceCheck);

        try {
            Response<Void> response = client.agentServiceRegister(newService);
            registered = true;
        } catch (Exception e) {
            throw e;
        }


    }

    private String getIp() throws ConsulRegistryException {
        try {
            String dHostIp = System.getenv("_D_HOST_IP");
            if (null != dHostIp){
                //云上环境_D_HOST_IP --dHostIp
                return dHostIp;
            }
            Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            NetworkInterface loopback = null;
            String iPV4;

            while (interfaceEnumeration.hasMoreElements()) {
                NetworkInterface networkInterface = interfaceEnumeration.nextElement();
                if (networkInterface.isUp()) {
                    if (networkInterface.isLoopback()){
                        loopback = networkInterface;
                    } else {
                        iPV4 = getIPv4(networkInterface);
                        if (null != iPV4){
                            return iPV4;
                        }
                    }
                }
            }
            if (null != loopback){
                iPV4 = getIPv4(loopback);
                if (null != iPV4){
                    return iPV4;
                }
            }
            throw new Exception("can not get local ipv4");
        } catch (Exception e) {
            throw new ConsulRegistryException(e);
        }
    }

    private String getIPv4(NetworkInterface networkInterface) {
        String iPV4 = null;
        Enumeration inetAddresses = networkInterface.getInetAddresses();
        while (inetAddresses.hasMoreElements()) {
            InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
            if (inetAddress instanceof Inet4Address){
                iPV4 = inetAddress.getHostAddress();
            }
        }
        return iPV4;
    }

    @Override
    public void deregister() {
        if (registered){
            client.agentServiceDeregister(newService.getId());
        }
    }

    @Override
    public void close() {
        this.deregister();
    }

    public int getPort(){
        int applicationPort = getApplicationPort();
        String dHostIp = System.getenv("_D_HOST_IP");
        if (null != dHostIp) {
            int paasPort = Integer.parseInt(System.getenv("_PAAS_PORT_" + applicationPort));
            //云上环境_PAAS_PORT_{}:{} applicationPort,paasPort
            return paasPort;
        } else {
            return applicationPort;
        }
    }

    private int getApplicationPort() {
        //读取 如果配置了manage.port,按照此端口注册,否则按照server.port
        if (null != managementServerPort && 0 < managementServerPort){
            return managementServerPort;
        }

        if (null == serverPort){
            //没有配置server.port, 注册spring默认端口
            return DEFAULT_PORT;
        }
        return serverPort;
    }
}
