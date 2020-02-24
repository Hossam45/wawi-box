package com.jkaref.wawi.backend;


import com.jkaref.wawi.api.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private static final int DEFAULT_LIMIT = 10;
    private static final int DEFAULT_OFFSET = 0;

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public List<Data> getAllProducts(){
        return getAllProductsLimit(DEFAULT_LIMIT, DEFAULT_OFFSET);
    }

    @RequestMapping("/products/{limit}/{offset}")
    public List<Data> getAllProductsLimit(@PathVariable int limit, @PathVariable int offset){
        return productService.getProductData(limit, offset);
    }

    @RequestMapping("/product/id/{id}")
    public List<Data> getProductById(@PathVariable String id){
       return getProductByIdLimit(id, DEFAULT_LIMIT, DEFAULT_OFFSET);
    }

    @RequestMapping("/product/id/{id}/{limit}/{offset}")
    public List<Data> getProductByIdLimit(@PathVariable String id, @PathVariable int limit, @PathVariable int offset){
        return productService.getProductDataById(id, limit, offset);
    }

    @RequestMapping("/product/title/{title}")
    public List<Data> getProductByTitle(@PathVariable String title){
        return getProductByTitleLimit(title, DEFAULT_LIMIT, DEFAULT_OFFSET);
    }

    @RequestMapping("/product/title/{title}/{limit}/{offset}")
    public List<Data> getProductByTitleLimit(@PathVariable String title, @PathVariable int limit, @PathVariable int offset){
        return productService.getProuductsDataByTitle(title, limit, offset);
    }

    @RequestMapping("/product/productionnumber/{herstellernummer}")
    public List<Data> getProductByProductionNumber(@PathVariable String herstellernummer) {
        return getProductByProductionNumberLimit(herstellernummer, DEFAULT_LIMIT, DEFAULT_OFFSET);
    }

    @RequestMapping("/product/productionnumber/{herstellernummer}/{limit}/{offset}")
    public List<Data> getProductByProductionNumberLimit(@PathVariable String herstellernummer, @PathVariable int limit, @PathVariable int offset){
        return productService.getProuductsDataByProductionNumber(herstellernummer, limit, offset);
    }

    @RequestMapping("/product/producername/{herstellername}")
    public List<Data> getProductByProducerName(@PathVariable String herstellername){
        return getProductByProducerNameLimit(herstellername, DEFAULT_LIMIT, DEFAULT_OFFSET);
    }

    @RequestMapping("/product/producername/{herstellername}/{limit}/{offset}")
    public List<Data> getProductByProducerNameLimit(@PathVariable String herstellername, @PathVariable int limit, @PathVariable int offset){
        return productService.getProductByProducerTitle(herstellername, limit, offset);
    }

    @RequestMapping("/product/providername/{liferantname}")
    public List<Data> getProductByProviderName(@PathVariable String liferantname){
        return getProductByProviderNameLimit(liferantname, DEFAULT_LIMIT, DEFAULT_OFFSET);
    }

    @RequestMapping("/product/providername/{liferantname}/{limit}/{offset}")
    public List<Data> getProductByProviderNameLimit(@PathVariable String liferantname, @PathVariable int limit, @PathVariable int offset){
        return productService.getProductByProviderName(liferantname, limit, offset);
    }

}
