package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.Announcement;
import net.lumae.api.repository.AnnouncerRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnnouncerController {
    @Autowired
    private final AnnouncerRepository announcer;

    public AnnouncerController(AnnouncerRepository announcer) {
        this.announcer = announcer;
    }

    @GetMapping("/announcements")
    public List<Announcement> all() {
        return announcer.findAll();
    }

    @GetMapping("/announcements/{id}")
    public String get(@PathVariable String id) {
        return announcer.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id, Announcement.scope)).message();
    }

    @GetMapping("/announcements/length")
    public int size(@PathVariable String id) {
        return (int)announcer.count();
    }

    @PostMapping("/announcements")
    public int add(@RequestBody String name, @RequestBody String message) {
        announcer.insert(new Announcement(name, message));
        return (int)announcer.count();
    }

    @DeleteMapping("/announcements")
    public void delete(@RequestBody String name) {
        announcer.deleteById(name);
    }
}
