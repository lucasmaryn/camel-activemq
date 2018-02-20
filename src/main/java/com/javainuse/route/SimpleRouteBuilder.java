package com.javainuse.route;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {
    public void configure() throws Exception {
        from("file:C:/camel-InputFolder").split().tokenize("\n").to("direct:test");

//Content Based routing- Route the message based on the token it contains.
        from("direct:test").
                choice().
                when(body().contains("javainuse1"))
                .to("jms:queue:javainuse1").
                when(body().contains("javainuse2"))
                .to("jms:queue:javainuse2")
                .when(body().contains("javainuse3"))
                .to("jms:queue:javainuse3").
                otherwise().
                to("jms:queue:otherwise");

    }
}
