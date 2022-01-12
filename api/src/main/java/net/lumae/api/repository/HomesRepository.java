package net.lumae.api.repository;

import dev.samkist.lumae.sagittarius.data.models.api.GlobalHomes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = GlobalHomes.scope, path = GlobalHomes.scope)
public interface HomesRepository extends MongoRepository<GlobalHomes, String> {

}
