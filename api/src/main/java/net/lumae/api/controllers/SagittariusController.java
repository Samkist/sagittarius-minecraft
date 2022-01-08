package net.lumae.api.controllers;

import net.lumae.api.ApiApplication;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiApplication.sagittariusRoute)
public @interface SagittariusController {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
