package org.example.admin.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject, "create_time", Date::new, Date.class);
        strictInsertFill(metaObject, "update_time", Date::new, Date.class);
        strictInsertFill(metaObject, "del_flag", () -> 0, Integer.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictUpdateFill(metaObject, "update_time", Date::new, Date.class);
    }
}
