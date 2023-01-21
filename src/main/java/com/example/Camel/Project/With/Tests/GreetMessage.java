package com.example.Camel.Project.With.Tests;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import static org.apache.camel.LoggingLevel.ERROR;

@Component
@ConditionalOnProperty(name="jss.camel.hello.enabled",havingValue="true")
public class GreetMessage extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:greeting").id("greeting")
                .log(ERROR,"Hello ${body}")
                .choice()
                .when().simple("${body} contains 'Team'")
                .log(ERROR,"I like working with Teams")
                .otherwise()
                .log(ERROR,"Solo fighter:)")
                .end()
                .to("direct:finishGreeting");

        from("direct:finishGreeting")
                .log(ERROR,"Bye ${body}");
    }
}
