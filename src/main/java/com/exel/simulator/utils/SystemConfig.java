/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exel.simulator.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author brixzen
 */
public class SystemConfig {

    public Properties prop;
    private static SystemConfig instance = null;

    private SystemConfig() {
        prop = new Properties();
        try {
            InputStream input = null;
            prop.load(new FileInputStream( "./config/api_config.conf"));
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    public static SystemConfig getInstance(){
        if(instance==null){
            instance = new SystemConfig();
        }
        return instance;
    }
}
