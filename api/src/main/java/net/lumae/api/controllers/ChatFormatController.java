package net.lumae.api.controllers;

import net.lumae.api.repository.ChatFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@SagittariusController
public class ChatFormatController {

    @Autowired
    private final ChatFormatRepository formats;

    public ChatFormatController(ChatFormatRepository formats) {
        this.formats = formats;
    }



}
