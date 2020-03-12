package com.coding.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

//自动填充增强类
@Slf4j
@Component
public class MyMateObjectHandler implements MetaObjectHandler{

    //插入填充
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insertFill running...");
        //setFieldValByName(自动填充的字段,填充的值,metaObject);
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    //更新填充
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("updateFill running...");
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
