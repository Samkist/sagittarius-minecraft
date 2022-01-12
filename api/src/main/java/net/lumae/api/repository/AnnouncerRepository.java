package net.lumae.api.repository;

import dev.samkist.lumae.sagittarius.data.models.Announcement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = Announcement.scope, path = Announcement.scope)
public interface AnnouncerRepository extends MongoRepository<Announcement, String> {
}
