/**
 * (c) [j]karef GmbH. All rights reserved.
 */
package com.jkaref.wawi.frontend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;


public class TestSimpleController {

    

    RestTemplate restTemplate = new RestTemplate();

   @GetMapping("/")
   public String homePage(@RequestParam(name = "name",required = false, defaultValue = "Wawi-Box") String name, Model model){
       model.addAttribute("name", name);
       return "home";
   }
   @GetMapping("products/")
    public List<JSONProduct> getAllProducts(int limit , int offset){
       JSONProduct[] tempTemplate = restTemplate.getForObject("http://localhost:8090/product/" + limit + "/" + offset, JSONProduct[].class);
       ArrayList<JSONProduct> result = new ArrayList<>(Arrays.asList(tempTemplate));
       return result;
   }
   @GetMapping("/title")
    public List<JSONProduct> getProductByTitle(int limit, int offset, String title){
       JSONProduct[] tempTemplate = restTemplate.getForObject("http://localhost:8090/product/title/" + title + "/" + limit + "/" + offset, JSONProduct[].class);
       ArrayList<JSONProduct> result = new ArrayList<>(Arrays.asList(tempTemplate));
       return result;
   }

   @GetMapping("/productname")
    public List<JSONProduct> getProductByProductName(int limit, int offset, String productname){
       JSONProduct[] tempTemplate = restTemplate.getForObject("http://product/productname" + productname + "/" + limit + "/" + offset, JSONProduct[].class);
       ArrayList<JSONProduct> result = new ArrayList<>(Arrays.asList(tempTemplate));
       return result;
   }









    @Value("${spring.application.name}")
    String appName;


    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("productList", appName);
        return "productTable";
    }

    @GetMapping("/test//{limit}/{offset}")
    public String testPage(Model model, @PathVariable int limit, @PathVariable int offset) {
        System.out.println(limit);
        return "productTable";
    }

    @GetMapping("/byID")
    public String getById(Model model){
        JSONProduct[] quotes = restTemplate.getForObject("http://localhost:8090/products", JSONProduct[].class);
        model.addAttribute("productList", quotes);
        return "productTable";

    }

    @GetMapping("/byTitle/{title}")
    public String getById(Model model, @PathVariable String title){
        JSONProduct[] quotes = restTemplate.getForObject("http://localhost:8090/product/title/"+title, JSONProduct[].class);
        model.addAttribute("productList", title);
        return "productTable";
    }
    @GetMapping("/byHerstellernummer/{herstellernummer}")
    public String getByHerstellernummer(Model model, @PathVariable String herstellernummer){
        JSONProduct[] quotes = restTemplate.getForObject("http://localhost:8090/product/herstellernummer/" + herstellernummer, JSONProduct[].class);
        model.addAttribute("propductList",herstellernummer);
        return "productTable";
    }


}