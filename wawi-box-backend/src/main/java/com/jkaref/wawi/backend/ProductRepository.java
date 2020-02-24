package com.jkaref.wawi.backend;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("my-mongo-repo")
@Qualifier("ProductRepository")
public interface ProductRepository extends MongoRepository<Product, String> {



}
