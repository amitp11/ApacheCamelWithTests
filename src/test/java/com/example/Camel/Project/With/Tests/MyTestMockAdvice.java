package com.example.Camel.Project.With.Tests;

import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.apache.camel.reifier.RouteReifier.adviceWith;

public class MyTestMockAdvice extends CamelTestSupport {

    @Override
    public boolean isUseAdviceWith(){
        return true;
    }
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new MyClass();
        /*return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:greeting").to("mock:greetingResult");
            }
        };*/
    }
    @Test
    public void testMocksAreValid() throws Exception {
        RouteDefinition routeDefinition = context.getRouteDefinition("greeting");

        adviceWith(routeDefinition, context,
                new AdviceWithRouteBuilder(){
                    @Override
                    public void configure() throws Exception{
                        weaveAddLast().to("mock:finishGreeting");
                    }
                });

        context.start();

        MockEndpoint mockEndpoint = getMockEndpoint("mock:finishGreeting");
        mockEndpoint.expectedMessageCount(1);

        template.sendBody("direct:greeting","Team");
        mockEndpoint.assertIsSatisfied();
    }
}