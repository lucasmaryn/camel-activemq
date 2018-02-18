package com.javainuse.route;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {
    public void configure() throws Exception {
        from("file:C:/camel-InputFolder").split().tokenize("\n").to("jms:queue:javainuse");
    }
}
