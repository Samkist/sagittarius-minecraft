package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.Announcement;
import net.lumae.api.repository.AnnouncerRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/announcements")
    public List<Announcement> all() {
        return announcer.findAll();
    }

    @GetMapping("/announcements/{id}")
    public String get(@PathVariable String id) {
        return announcer.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id, Announcement.scope)).getMessage();
    }
    @GetMapping("/announcements/refresh")
    public void refresh() {
        List<Announcement> announcements = announcer.findAll();
        for (int i = 0; i < announcements.size(); i++) {
            for (Announcement announcement : announcements) {
                if (announcement.uid == String.valueOf(i)) {
                    loaded_announcements.add(i, announcement);
                    break;
                }
            }
        }
    }

    @GetMapping("/announcements/length")
    public int size(@PathVariable String id) {
        return (int)announcer.count();
    }

    @PostMapping("/announcement")
    public int add(@RequestBody String message) {
        announcer.insert(new Announcement(String.valueOf(loaded_announcements.size()), message));
        refresh();
        return loaded_announcements.size();
    }

    @DeleteMapping("/time/played")
    public void delete(@RequestBody String id) {
        announcer.deleteById(id);
        refresh();
    }
}
