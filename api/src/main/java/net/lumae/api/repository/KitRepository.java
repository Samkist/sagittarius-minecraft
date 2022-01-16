package net.lumae.api.repository;

import dev.samkist.lumae.sagittarius.data.models.gamemode.Kit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = Kit.scope, path = Kit.scope)
public interface KitRepository extends MongoRepository<Kit, String> {

}
