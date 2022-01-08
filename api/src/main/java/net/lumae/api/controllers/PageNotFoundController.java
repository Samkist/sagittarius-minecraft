package net.lumae.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageNotFoundController {
    @GetMapping("/**")
    public String handle() {
        return "404 Page not found.";
    }
}