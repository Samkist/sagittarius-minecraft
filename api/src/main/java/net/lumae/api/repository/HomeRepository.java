package net.lumae.api.repository;

import dev.samkist.lumae.sagittarius.data.models.Home;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Map;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = Home.scope, path = Home.scope)
public interface HomeRepository extends MongoRepository<Map<String, Home>, String> {

    Optional<Map<String, Home>> findById(@Param("id") String id);
}
