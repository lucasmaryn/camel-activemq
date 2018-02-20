package com.javainuse.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {
    public void configure() throws Exception {
        from("file:C:/camel-InputFolder").split().tokenize("\n").to("direct:test");

        //Recipient List- Dynamically set the recipients of the exchange
        //by creating the queue name at runtime
        from("direct:test")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        String recipient = exchange.getIn().getBody().toString();
                        String recipientQueue="jms:queue:"+recipient;
                        exchange.getIn().setHeader("queue", recipientQueue);
                    }
                }).recipientList(header("queue"));

    }
}
