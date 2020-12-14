package com.tencent.oa.eduservice.config;

import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.tencent.oa.eduservice.mapper")
public class EduConfig {

    /**
     * 逻辑删除组件
     * @return
     */
    @Bean
    public LogicSqlInjector logicSqlInjector(){
        return new LogicSqlInjector();
    }
}
