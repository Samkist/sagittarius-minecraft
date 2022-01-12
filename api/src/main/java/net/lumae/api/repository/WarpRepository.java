package net.lumae.api.repository;

import dev.samkist.lumae.sagittarius.data.models.api.GlobalWarps;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Warp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = GlobalWarps.scope, path = GlobalWarps.scope)
public interface WarpRepository extends MongoRepository<GlobalWarps, String> {

}
