package com.jkaref.wawi.api;

import com.jkaref.wawi.api.model.Data;
import com.jkaref.wawi.api.model.GsonUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
        Data data = new Data();
        GsonUtil g = new GsonUtil();
        g.toJson(data);
    }
}
