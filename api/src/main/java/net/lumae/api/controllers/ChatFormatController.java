package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.ChatFormat;
import net.lumae.api.repository.ChatFormatRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SagittariusController
public class ChatFormatController {

    @Autowired
    private final ChatFormatRepository formats;

    public ChatFormatController(ChatFormatRepository formats) {
        this.formats = formats;
    }

    @Cacheable("chatFormatCache")
    @GetMapping("/formats/chat")
    public List<ChatFormat> all() {
        return formats.findAll();
    }

    @Cacheable("chatFormatCache")
    @PostMapping("/formats/chat")
    public ChatFormat newFormat(@RequestBody ChatFormat format) {
        return formats.save(format);
    }

    @Cacheable("chatFormatCache")
    @GetMapping("/formats/chat/{id}")
    public ChatFormat one(@PathVariable String id) {
        return formats.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id, ChatFormat.scope));
    }

    @Cacheable("chatFormatCache")
    @DeleteMapping("/formats/chat/{id}")
    public void delete(@PathVariable String id) {
        formats.deleteById(id);
    }



}
