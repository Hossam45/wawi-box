package com.jkaref.wawi.api.model;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GsonUtilTest {

    @Test
    public void testSimpleJsonWithPrettyPrinting() {
        Data data = new Data();

        String json = GsonUtil.toJson(data);

        assertFalse(json.isEmpty());
        assertEquals(json, "{\n" +
                "  \"angebotes\": []\n" +
                "}");

        Data result = GsonUtil.fromJson(json);

        assertEquals(result, data);
    }

    @Test
    public void testSimpleJsonWithPrinting() throws IOException {
        String data = "";
        String line = "";
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/test/resources/test.json")
        );
        while ((line = bufferedReader.readLine()) != null){
            data += line;
        }

        Data result = GsonUtil.fromJson(data);
        System.out.println(result);
        assertEquals(result.getTitle(), "Seralene blau DSS-15 6/0 Packung 24 Stück");
    }
}