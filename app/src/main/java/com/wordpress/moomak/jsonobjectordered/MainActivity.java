package com.wordpress.moomak.jsonobjectordered;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    private LinkedHashMap<String,String> rangeList;
    private Map json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String response = "{\"range_list\":" +
                    "{\"somename_1\":\"somename_1\"," +
                    "\"somename_2\":\"somename_2\"," +
                    "\"somename_3\":\"somename_3\"," +
                    "\"somename_4\":\"somename_4\"," +
                    "\"somename_5\":\"somename_5\"," +
                    "\"somename_6\":\"somename_6\"," +
                    "\"somename_7\":\"somename_7\"}" +
                "}";

        JSONParser parser = new JSONParser();
        ContainerFactory containerFactory = new ContainerFactory(){
            public List creatArrayContainer() {
                return new LinkedList();
            }

            public Map createObjectContainer() {
                return new LinkedHashMap();
            }

        };

        try{
            json = (Map)parser.parse(response, containerFactory);
            rangeList = (LinkedHashMap<String, String>) json.get("range_list");

            Iterator iter = rangeList.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry entry = (Map.Entry)iter.next();
                System.out.println(entry.getKey() + " => " + entry.getValue());
            }

            System.out.println("==toJSONString()==");
            System.out.println(JSONValue.toJSONString(rangeList));
        }
        catch(ParseException pe){
            System.out.println(pe);
        }
    }
}
