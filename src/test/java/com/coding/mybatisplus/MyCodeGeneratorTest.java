package com.coding.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class MyCodeGeneratorTest {
    @Test
    public void codeGenerator(){
        //1. 创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2.全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Xiao");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成文件的时候是否会覆盖
        gc.setIdType(IdType.ID_WORKER_STR); //逐渐策略
        gc.setServiceName("%sService"); //所有自动生成的Service接口首字母I去除
        gc.setDateType(DateType.ONLY_DATE); //设置日期类型
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 生成的包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("mybatisplus");
        pc.setParent("com.coding");
        pc.setEntity("pojo");
        pc.setController("controller");
        pc.setMapper("mapper");
        pc.setService("service");
        mpg.setPackageInfo(pc);


        /**
         * 策略配置
         */
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("user"); //设置映射的表明：可以设置正则表达式，比如表名开头位edu的所有的表可以用："edu"+"_\\w*"
        strategy.setNaming(NamingStrategy.underline_to_camel); //数据库映射到实体类的策略
        //strategy.setTablePrefix("edu_"); //不生成表的前缀, 如果你的表有edu_的前缀，那么这个配置的作用就是在生成表的实体类后类名不会有edu_的前缀
        strategy.setEntityLombokModel(true); //自动添加lombook的注解
        //逻辑删除
        strategy.setLogicDeleteFieldName("is_deleted"); //逻辑删除的字段名
        strategy.setEntityBooleanColumnRemoveIsPrefix(true); //去除布尔值的 is_ 前缀
        //自动填充
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);

        strategy.setVersionFieldName("version"); //乐观锁的列
        strategy.setRestControllerStyle(true);  //RestFul
        strategy.setControllerMappingHyphenStyle(true); //url 驼峰命名 转化为 _
        mpg.setStrategy(strategy);

        //执行
        mpg.execute();
    }
}
