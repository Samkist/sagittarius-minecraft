package net.lumae.api.repository;

import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = MilkyPlayer.scope, path = MilkyPlayer.scope)
public interface MilkyPlayerRepository extends MongoRepository<MilkyPlayer, String> {

    List<MilkyPlayer> findByLastUsername(@Param("lastUsername") String lastUsername);

}
