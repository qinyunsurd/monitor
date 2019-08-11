此项目运用springboot+actuator+prometheus+grafana+consul,node_export的搭建,一键启动,一键停止,
自定义指标以及simple-json的使用;


resources目录下是需要用到的基本资源,包含:
    bin: 启动,停止脚本;
    cfg: 配置文件;
    datasource: 数据源插件(simple_json);
    exporter-install: node_exporter相关;
    install: prometheus,grafana,consul相关;

