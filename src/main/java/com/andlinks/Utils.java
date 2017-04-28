package com.andlinks;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by 王凯斌 on 2017/4/28.
 */
public class Utils {


    public static void templateFileOutput(String templateName,
                                          String fileName,
                                          String exportPath,
                                          Map<String, Object> parms,
                                          String templatePath
    ) throws Exception {


        Configuration cfg = new Configuration(Configuration.getVersion());
        cfg.setDirectoryForTemplateLoading(new File(templatePath));
        Template t = cfg.getTemplate(templateName);
        Writer out = new OutputStreamWriter(new FileOutputStream(
                exportPath + fileName), "UTF-8");
        t.process(parms, out);
    }

    public static String templateStringOutput(String content,
                                              Map<String, Object> parms
    ) throws Exception {

        Configuration cfg = new Configuration(Configuration.getVersion());
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.getVersion()));

        Template t = new Template("default", new StringReader(content), cfg);

        Writer out = new StringWriter();
        t.process(parms, out);

        return out.toString();
    }

    public static Map<String, Object> jsonFileToMap(String jsonName) throws Exception {

        Resource resource = new ClassPathResource(jsonName);

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeRef
                = new TypeReference<HashMap<String, Object>>() {
        };
        return mapper.readValue(resource.getFile(), typeRef);
    }

    public static Map<String, Object> jsonStringToMap(String content) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeRef
                = new TypeReference<HashMap<String, Object>>() {
        };
        return mapper.readValue(content, typeRef);
    }

    public static String getResourceContent(String fileName) throws Exception {


        return new Scanner(new ClassPathResource(fileName).getFile()).useDelimiter("\\Z").next();
    }

}
