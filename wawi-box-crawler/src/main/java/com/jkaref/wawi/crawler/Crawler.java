package com.jkaref.wawi.crawler;

import com.jkaref.wawi.api.ProductLocation;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

public class Crawler {

    private static final Logger LOG = LoggerFactory.getLogger(Crawler.class);

    private Crawler() {
    }

    public static InputStream crawlProductLocations(String url) throws
            IOException, IllegalArgumentException {
        if (url == null) {
            throw new IllegalArgumentException("Url must not be null!");
        }

        LOG.info("[crawlProductLocations] - Crawling product locations at {}", url);

        return new URL(url).openStream();

    }

    public static InputStream crawlProduct(ProductLocation location, String api, String prefix)
            throws IOException {

        return crawlProductLocations(buildUrl(location, api, prefix));

    }

    public static String buildUrl(final ProductLocation location, final String api, final String prefix) {
        String retValue = null;

        if (api == null) {
            throw new IllegalArgumentException("API must not be null");
        }

        if (prefix == null) {
            throw new IllegalArgumentException("Prefix must not be null");
        }

        final String name = location.getLocation().replaceAll(prefix, "");
        if (api.endsWith("/"))
            retValue = String.format("%s%s/1", api, name);

        else
            retValue = String.format("%s/%s/1", api, name);

        return retValue;
    }

    public static void insertJson(String json) throws IOException {


        MongoClient mongoClient = new MongoClient("localhost", 27017);

        MongoDatabase db = mongoClient.getDatabase("testJson");
        MongoCollection jsonDB = db.getCollection("json");
        DBObject dbObject = (DBObject) JSON.parse(json);

        jsonDB.insertOne(new Document(dbObject.toMap()));

        FindIterable jsonDoc = jsonDB.find();
        Iterator iterator = jsonDoc.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


}
