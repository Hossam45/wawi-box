package com.jkaref.wawi;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WawiProperties {

    @Value("${wawibox.sitemap}")
    public String sitemap;

    @Value("${wawibox.heartbeat-interval}")
    public int heartbeat;

    @Value("${wawibox.api-url}")
    public String apiurl;

    @Value("${wawibox.product-prefix}")
    public String prefix;

    @Value("${wawibox.crawler.enabled}")
    public Boolean enable;

}
