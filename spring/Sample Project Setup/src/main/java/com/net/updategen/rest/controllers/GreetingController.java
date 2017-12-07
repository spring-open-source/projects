package com.net.updategen.rest.controllers;

import com.net.updategen.persistence.models.Greeting;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample rest resource controller
 *
 * @author hardiku
 */
@RestController
public class GreetingController
{

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping ("/greeting")
    public Greeting greeting(@RequestParam (value = "name", defaultValue = "World") String name) throws IOException
    {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
