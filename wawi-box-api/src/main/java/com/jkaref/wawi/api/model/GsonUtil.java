package com.jkaref.wawi.api.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GsonUtil {

    public static String toJson(Data data){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(data);
        System.out.println(json);
        return json;
    }

    public static Data fromJson(String s) {
        Object wProdukt = null;
        JSONArray angebote = null;
        try {
            JSONParser parser = new JSONParser();
            if(s != null){
                JSONObject jsonObject = (JSONObject) parser.parse(s);
                if(jsonObject!= null){
                    JSONObject data = (JSONObject) jsonObject.get("data");
                    if(data != null){
                        wProdukt = data.get("wProdukt");
                        angebote = (JSONArray) data.get("_Angebote");
                    }
                }
            }
        }catch (Exception ex){
            System.out.println("convert Error -> " + ex);
        }

        Gson gson = new GsonBuilder().create();
        Data result = new Data();
        if(wProdukt!= null)
            result = gson.fromJson(wProdukt.toString(), Data.class);
        if(angebote!=null) {
            for (int i = 0; i < angebote.size(); i++) {
                JSONObject angebot = (JSONObject) angebote.get(i);
                result.getAngebotes().add(gson.fromJson(angebot.get("Data").toString(), Angebote.class));
            }
        }

        return result;
    }





}
