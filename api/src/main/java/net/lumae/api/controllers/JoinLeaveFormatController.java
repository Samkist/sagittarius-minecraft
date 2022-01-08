package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.JoinLeaveFormat;
import net.lumae.api.repository.JoinLeaveFormatRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SagittariusController
public class JoinLeaveFormatController {

    @Autowired
    private final JoinLeaveFormatRepository formats;


    public JoinLeaveFormatController(JoinLeaveFormatRepository formats) {
        this.formats = formats;
    }

    @Cacheable("joinLeaveFormatCache")
    @GetMapping("/formats/join-leave")
    public List<JoinLeaveFormat> all() {
        return formats.findAll();
    }

    @Cacheable("joinLeaveFormatCache")
    @PostMapping("/formats/join-leave")
    public JoinLeaveFormat newFormat(@RequestBody JoinLeaveFormat format) {
        return formats.save(format);
    }

    @Cacheable("joinLeaveFormatCache")
    @GetMapping("/formats/join-leave/{id}")
    public JoinLeaveFormat one(@PathVariable String id) {
        return formats.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id, JoinLeaveFormat.scope));
    }

    @Cacheable("joinLeaveFormatCache")
    @DeleteMapping("/formats/join-leave/{id}")
    public void delete(@PathVariable String id) {
        formats.deleteById(id);
    }

}