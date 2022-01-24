package com.exel.simulator.data;

import com.exel.simulator.utils.SystemConfig;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Constant variable to keep all configuration in memory
 */
public class Constant {

    public final static Properties prop = SystemConfig.getInstance().prop;

    public static int thread_incoming_api = Integer.parseInt(prop.getProperty("thread_incoming_api", "10"));

    public static AtomicInteger counter = new AtomicInteger();
}
