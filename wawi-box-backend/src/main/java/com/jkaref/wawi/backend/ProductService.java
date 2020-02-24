package com.jkaref.wawi.backend;

import com.jkaref.wawi.api.model.Data;
import com.jkaref.wawi.api.model.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {


    public ProductRepository getRepository() {
        return repository;
    }

    @Autowired
    @Qualifier("ProductRepository")
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    private ProductRepository repository;

    @Autowired
    private MongoTemplate operations;

    private List<Data> mapProducts(List<Product> products){

        List<Data> result = new ArrayList<>();

        for (Product product : products) {
            if(product != null)
                result.add(GsonUtil.fromJson(product.getJson()));
        }

        return result;
    }

    public List<Data> getProductData(int limit, int offset) {

        Query query = new Query();

        query.limit(limit);
        query.skip(offset);

        List<Product> products = operations.find(query, Product.class);

        return mapProducts(products);
    }

    public List<Data> getProductDataById(String id, int limit, int offset) {
        Query query = new Query();
        query.addCriteria(Criteria.where("json.data.wProdukt._ID").is(id));
        query.limit(limit);
        query.skip(offset);

        List<Product> products = operations.find(query, Product.class);

        return mapProducts(products);

    }

    public List<Data> getProuductsDataByTitle(String Title, int limit, int offset) {
        Query query = new Query();
        query.addCriteria(Criteria.where("json.data.wProdukt.Title").is(Title));
        query.limit(limit);
        query.skip(offset);

        List<Product> products = operations.find(query, Product.class);

        return mapProducts(products);
    }

    public List<Data> getProuductsDataByProductionNumber(String herstellernummer, int limit, int offset) {
        Query query = new Query();
        query.addCriteria(Criteria.where("json.data.wProdukt.Herstellernummer").is(herstellernummer));
        query.limit(limit);
        query.skip(offset);

        List<Product> products = operations.find(query, Product.class);

        return mapProducts(products);
    }

    public List<Data> getProductByProducerTitle(String herstellerName, int limit, int offset) {

        Query query = new Query();
        query.addCriteria(Criteria.where("json.data.wProdukt._Herstellername").is(herstellerName));
        query.limit(limit);
        query.skip(offset);

        List<Product> products = operations.find(query, Product.class);

        return mapProducts(products);
    }

    public List<Data> getProductByProviderName(String liferantName, int limit, int offset) {

        Query query = new Query();
        query.addCriteria(Criteria.where("json.data._Angebote.Data.Lieferant_Name").is(liferantName));
        query.limit(limit);
        query.skip(offset);

        List<Product> products = operations.find(query, Product.class);

        return mapProducts(products);
    }

}
