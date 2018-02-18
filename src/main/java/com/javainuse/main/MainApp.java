package com.javainuse.main;

import com.javainuse.route.SimpleRouteBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

public class MainApp {
    public static void main(String[] args) {
        SimpleRouteBuilder routeBuild = new SimpleRouteBuilder();
        CamelContext ctx = new DefaultCamelContext();

//        configuration of jms component
        ConnectionFactory connFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
        ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connFactory));
        try {
            ctx.addRoutes(routeBuild);
            ctx.start();
            Thread.sleep(5 * 1000 * 60);
            ctx.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
