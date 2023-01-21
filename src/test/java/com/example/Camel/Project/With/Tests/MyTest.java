package com.example.Camel.Project.With.Tests;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

public class MyTest  extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new MyClass();
        /*return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:greeting").log(LoggingLevel.ERROR, "${body}");
            }
        };*/
    }
    @Test
    public void testMocksAreValid() throws InterruptedException {
        System.out.println("Sending 1");
        template.sendBody("direct:greeting","Team");

        System.out.println("Sending 1");
        template.sendBody("direct:greeting","Me");
    }
}
