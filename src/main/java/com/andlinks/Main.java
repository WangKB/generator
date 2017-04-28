package com.andlinks;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 王凯斌 on 2017/4/28.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("application.properties");
        prop.load(stream);


        String content = Utils.templateStringOutput(Utils.getResourceContent("relation.ftl"), Utils.jsonFileToMap("parms.json"));
        for (Map<String, String> map : (List<Map<String, String>>) Utils.jsonStringToMap(content).get("relations")) {
            Utils.templateFileOutput(map.get("templateName"),
                    map.get("fileName"),
                    prop.getProperty("target.project.root.path") + map.get("exportPath"),
                    Utils.jsonFileToMap("parms.json"),
                    "src/main/resources/templates"
            );
        }


    }


}
