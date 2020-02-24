/**
 * (c) [j]karef GmbH. All rights reserved.
 */
package com.jkaref.wawi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class SimpleController {

    RestTemplate restTemplate = new RestTemplate();

    @Value("${spring.application.name}")
    String appName;


    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/test/{limit}/{offset}")
    public String testPage(Model model, @PathVariable int limit, @PathVariable int offset) {
        System.out.println(limit);
        return "test";
    }

    @GetMapping("/byID")
    public String getById(Model model){
        DataJSON[] quotes = restTemplate.getForObject("http://localhost:8090/products", DataJSON[].class);
        model.addAttribute("dataList", quotes);
        return "productTable";

    }

    @GetMapping("/byTitle/{title}")
    public String getById(Model model, @PathVariable String title){
        DataJSON[] quotes = restTemplate.getForObject("http://localhost:8090/product/title/"+title, DataJSON[].class);
        model.addAttribute("appName", title);
        return "productTable";
    }
    @GetMapping("/byHerstellername/{herstellername}")
    public String getByHerstellername(Model model , @PathVariable String herstellername){
        DataJSON[] quotes = restTemplate.getForObject("http://localhost:8090/product/herstellername/" + herstellername, DataJSON[].class);
        model.addAttribute("dataList", quotes);
        return "productTable";
    }

    @GetMapping("/byHerstellernummer/{herstellernummer}")
    public String getByHerstellernummer(Model model ,@PathVariable String herstellernummer ){
        DataJSON[] quotes = restTemplate.getForObject("http://localhost:8090/product/herstellernummer/" + herstellernummer, DataJSON[].class);
        model.addAttribute("dataList" , quotes);
        return "productTable";
    }

    @GetMapping("/byPreisAb/{preisAb}")
    public String getByPreisAb(Model model ,@PathVariable String preisAb ){
        DataJSON[] quotes = restTemplate.getForObject("http://localhost:8090/product/preisAb/" + preisAb, DataJSON[].class);
        model.addAttribute("dataList" , quotes);
        return "productTable";
    }

    @GetMapping("/byPreisBis/{preisBis}")
    public String getByPreisBis(Model model ,@PathVariable String preisBis ){
        DataJSON[] quotes = restTemplate.getForObject("http://localhost:8090/product/preisBis/" + preisBis, DataJSON[].class);
        model.addAttribute("dataList" , quotes);
        return "productTable";
    }

}
