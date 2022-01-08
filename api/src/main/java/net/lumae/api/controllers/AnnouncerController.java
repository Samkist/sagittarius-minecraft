package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.Announcement;
import net.lumae.api.repository.AnnouncerRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SagittariusController
public class AnnouncerController {
    @Autowired
    private final AnnouncerRepository announcer;
    private List<Announcement> loaded_announcements;

    public AnnouncerController(AnnouncerRepository announcer) {
        this.announcer = announcer;
    }

    @Cacheable("playerCache")
    @GetMapping("/announcements")
    public List<Announcement> all() {
        return announcer.findAll();
    }

    @Cacheable("playerCache")
    @GetMapping("/announcements/{id}")
    public String get(@PathVariable String id) {
        return announcer.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id, Announcement.scope)).getMessage();
    }

    @Cacheable("playerCache")
    @GetMapping("/announcements/length")
    public int size(@PathVariable String id) {
        return (int)announcer.count();
    }

    @Cacheable("playerCache")
    @PostMapping("/announcement")
    public int add(@RequestBody String message) {
        announcer.insert(new Announcement(String.valueOf(loaded_announcements.size()), message));
        loaded_announcements = all();
        return loaded_announcements.size();
    }

    @Cacheable("playerCache")
    @DeleteMapping("/time/played")
    public void delete(@RequestBody String id) {
        announcer.deleteById(id);
    }
}
