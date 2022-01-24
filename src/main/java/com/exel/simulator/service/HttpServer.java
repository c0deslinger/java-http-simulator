package com.exel.simulator.service;

import com.exel.simulator.controller.SimpleController;
import com.exel.simulator.data.Constant;
import com.exel.simulator.utils.SystemConfig;
import spark.Service;

import java.util.Properties;

public class HttpServer {

    public void start(String mode, String port, String path, String response, String respType) {
        Properties prop = SystemConfig.getInstance().prop;
        if (mode.equals("https")) {
            Service https = Service.ignite();
            https.initExceptionHandler(e -> {
                e.printStackTrace();
                System.exit(100);
            });
            https.secure(prop.getProperty("https_key_file", "keystore.jks"),
                    prop.getProperty("https_password", null),
                    prop.getProperty("https_truststore_file", null),
                    prop.getProperty("https_truststore_password", null));
            https.port(Integer.parseInt(port));
            https.threadPool(Constant.thread_incoming_api);
            https.internalServerError("Error : 500 internal server error");
            https.get("/"+path, new SimpleController(response, respType));
            https.post("/"+path, new SimpleController(response, respType));
            System.out.println("Https listener initialized at port "+port);
        }else{
            Service http = Service.ignite();
            http.initExceptionHandler(e -> {
                e.printStackTrace();
                System.exit(100);
            });
            http.port(Integer.parseInt(port));
            http.threadPool(Constant.thread_incoming_api);
            http.internalServerError("Error : 500 internal server error");
            http.get("/"+path, new SimpleController(response, respType));
            http.post("/"+path, new SimpleController(response, respType));
            System.out.println("Http listener initialized at port "+port);
        }
    }

}
