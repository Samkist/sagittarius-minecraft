package net.lumae.api.repository;

import dev.samkist.lumae.sagittarius.data.models.Homes;
import dev.samkist.lumae.sagittarius.data.models.JoinLeaveFormat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = JoinLeaveFormat.scope, path = JoinLeaveFormat.scope)
public interface JoinLeaveFormatRepository extends MongoRepository<JoinLeaveFormat, String> {

}
