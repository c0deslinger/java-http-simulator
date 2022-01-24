package com.exel.simulator;

import com.exel.simulator.service.HttpServer;

public class Main {
    public static void main(String[] args) {
        String mode = args[0];
        String port = args[1];
        String path = args[2];
        String respType = args[3];
        String response = args[4];
        HttpServer httpServer = new HttpServer();
        System.out.println("Starting "+mode+" server with port: "+port+" path: "+path);
        httpServer.start(mode, port, path, response, respType);
    }
}
