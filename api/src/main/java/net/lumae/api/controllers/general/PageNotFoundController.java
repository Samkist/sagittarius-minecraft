package net.lumae.api.controllers.general;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageNotFoundController {
    @GetMapping("/404")
    public String handle() {
        return "404 Page not found.";
    }
}