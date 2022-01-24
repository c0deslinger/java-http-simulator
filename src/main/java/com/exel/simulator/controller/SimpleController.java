package com.exel.simulator.controller;

import com.exel.simulator.data.Constant;
import com.exel.simulator.utils.DataUtils;
import com.exel.simulator.utils.DateUtils;
import org.apache.commons.io.IOUtils;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.servlet.http.Part;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class SimpleController implements Route {

    private final String strResponse;
    private final String respType;

    public SimpleController(String strResponse, String respType) {
        this.strResponse = strResponse;
        this.respType = respType;
    }

    @Override
    public Object handle(Request request, Response response) {
        long start = System.currentTimeMillis();
        String trxId = DataUtils.generateTrxId();
        String logPrefix = DateUtils.getDateTime()+"|"+trxId+"|";
        Collection<Part> parts;
        System.out.println(logPrefix+"<< Http Request Start >>");
        boolean timeoutAtFirst = false;
        long timeoutValue = 1000;
        try {
            for (String key : request.headers()) {
                System.out.println(logPrefix+"[Request header] "+key+" : "+request.headers(key));
            }
            for (String key : request.queryParams()) {
                System.out.println(logPrefix+"[Request param] "+key+" : "+request.queryParams(key));
                if(key.equals("timeoutAtFirst")){
                    timeoutAtFirst = Boolean.parseBoolean(request.queryParams(key));
                }else if(key.equals("timeoutValue")){
                    timeoutValue = Integer.parseInt(request.queryParams(key));
                }
            }

            //handling http post
            if (request.contentType() != null && request.contentType().contains("multipart/form-data")) {
                parts = request.raw().getParts();

                for (Part part : parts) {
                    if(part.getContentType()==null){
                        String key = part.getName();
                        String val  = IOUtils.toString(request.raw()
                                .getPart(part.getName()).getInputStream(), StandardCharsets.UTF_8);
                        System.out.println(logPrefix+"[Request param] "+key+" : "+val);
                        if(key.equals("timeoutAtFirst")){
                            timeoutAtFirst = Boolean.parseBoolean(request.queryParams(key));
                        }else if(key.equals("timeoutValue")){
                            timeoutValue = Integer.parseInt(val);
                        }
                    }
                }
            }
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
        }
        if(timeoutAtFirst && (Constant.counter.get() % 2 == 0)){
            try {
                Thread.sleep(timeoutValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        response.type(respType);
        response.body(strResponse);
        System.out.println(logPrefix+"[Response body] "+strResponse);
        System.out.println(logPrefix+"[Response type] "+respType);
        System.out.println(logPrefix+"[Response time] "+(System.currentTimeMillis()-start));
        System.out.println(logPrefix+"<< Http Request End >>");

        response.header("Strict-Transport-Security", "max-age=31536000");
        Constant.counter.incrementAndGet();
        return response.body();
    }


}