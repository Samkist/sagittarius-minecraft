package net.lumae.api.controllers;

import net.lumae.api.ApiApplication;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(ApiApplication.sagittariusRoute)
public @interface SagittariusController {
    @AliasFor(annotation = RestController.class)
    String value() default "";
}
