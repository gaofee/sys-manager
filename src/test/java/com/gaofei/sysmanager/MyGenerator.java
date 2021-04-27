package com.gaofei.sysmanager;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyGenerator {

    public static void main(String[] args) {
        //全局配置
        GlobalConfig globalConfig=new GlobalConfig();
        globalConfig.setAuthor("gaofei")//设置作者
                .setActiveRecord(true)//是否支持AR模式
                .setOutputDir("C:\\Users\\gaofee\\Desktop")   //生成文件的路径
                .setFileOverride(true)   //文件覆盖
                .setEnableCache(false)  //是否开启二级缓存
                .setIdType(IdType.AUTO); //主键策略

        //数据源配置
        DataSourceConfig dataSourceConfig=new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://localhost:3306/sys_permission?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("gaofei");

        //策略配置
        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setCapitalMode(true)  //全局大写
//                .setDbColumnUnderline(true) //数据库表名,字段名是否使用下划线
                .setEntityLombokModel(true)//使用lombok
                .setRestControllerStyle(true) //使用restController
                .setNaming(NamingStrategy.underline_to_camel) ; //数据库表映射到实体的命名策略
//                .setInclude("edoc_category","edoc_entry");//表名,不指定表名生成全部
        // .setTablePrefix("tbl_");//表前缀

        //包名策略
        PackageConfig packageConfig=new PackageConfig();
        packageConfig.setParent("com.gaofei.sysmanager")
                .setEntity("domain")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setXml("mapper");

        AutoGenerator autoGenerator=new AutoGenerator();
        autoGenerator.setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setGlobalConfig(globalConfig)
                .setStrategy(strategyConfig);
        autoGenerator.execute();
    }
}
