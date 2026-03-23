package ek.osnb.app.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    public record Response(String message) {
    }

    @GetMapping("/hello")
    public Response hello() {
        return new Response("Hello, World!");
    }

    @GetMapping("/test")
    public Response test() {
        return new Response("Test endpoint");
    }
}
