package com.jkaref.wawi.models.backend;

import com.jkaref.wawi.api.model.Data;
import com.jkaref.wawi.backend.Product;
import com.jkaref.wawi.backend.ProductService;
import org.bson.Document;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application.properties")
public class ProductServiceIT {

    @Before
    public void addTestData() throws IOException
    {
        ClassLoader classLoader = getClass().getClassLoader();
        String data = "";
        String line;
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(classLoader.getResource("test.json").getPath())
        );
        while ((line = bufferedReader.readLine()) != null){
            data += line;
        }
        Product product = new Product();
        product.id = "5b1515d8ff568328b49f2cb2";
        product.json = Document.parse(data);
        productService.getRepository().save(product);

        data = "";
        bufferedReader = new BufferedReader(
                new FileReader(classLoader.getResource("test_2.json").getPath())
        );
        while ((line = bufferedReader.readLine()) != null){
            data += line;
        }
        product = new Product();
        product.id = "5b1515d8ff568328b49f2cb1";
        product.json = Document.parse(data);
        productService.getRepository().save(product);

    }

    @After
    public void removeTestData(){
        Optional<Product> product = productService.getRepository().findById("5b1515d8ff568328b49f2cb2");
        productService.getRepository().delete(product.get());
        product = productService.getRepository().findById("5b1515d8ff568328b49f2cb1");
        productService.getRepository().delete(product.get());

    }

    @Test
    public void testGetProductDataByTitle() {

        String title = "Seralene blau DSS-15 6/0 Packung 24 Stück";

        List<Data> byHerstellername = productService.getProuductsDataByTitle(title, 10, 0);

        Assert.assertNotNull(byHerstellername);
        Assert.assertTrue(byHerstellername.size() == 0);
    }
    @Test
    public void testgetProductDataByHerstellername(){
        String herstellername = "Serag-Wiessner";

        List<Data> byHerstellername = productService.getProductByProducerTitle(herstellername, 10, 0);

        Assert.assertNotNull(byHerstellername);
        Assert.assertTrue(byHerstellername.size() == 1);

    }

    @Test
    public void testGetProuductsDataByHerstellernummer() {
        String herstellernummer ="G11B77Y0";

        List<Data> byHerstellernummer = productService.getProuductsDataByProductionNumber(herstellernummer, 10, 0);

        Assert.assertNotNull(byHerstellernummer);
        Assert.assertTrue(byHerstellernummer.size() == 1);

    }

    @Test
    public void testGetProductDataById(){

        String id = "pbpeol";

        List<Data> byID = productService.getProductDataById(id, 10, 0);

        Assert.assertNotNull(byID);
        Assert.assertTrue(byID.size() == 1);

    }

    @Test
    public void testGetByLiferantName(){
        String name = "SICO Dentaldepot GmbH";

        List<Data> byLiferantName = productService.getProductByProviderName(name, 10, 0);
        System.out.println(byLiferantName);
        Assert.assertNotNull(byLiferantName);
        Assert.assertTrue(byLiferantName.size() == 2);
    }

    private ProductService productService = new ProductService();
}