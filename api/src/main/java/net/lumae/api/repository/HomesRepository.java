package net.lumae.api.repository;

import dev.samkist.lumae.sagittarius.data.models.Homes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Map;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = Homes.scope, path = Homes.scope)
public interface HomesRepository extends MongoRepository<Homes, String> {

}
