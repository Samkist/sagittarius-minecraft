package net.lumae.api.repository;

import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import dev.samkist.lumae.sagittarius.data.models.Warp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = MilkyPlayer.scope, path = MilkyPlayer.scope)
public interface WarpRepository extends MongoRepository<Warp, String> {

}
