package net.lumae.api.repository;

import dev.samkist.lumae.sagittarius.data.models.global.ChatFormat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = ChatFormat.scope, path = ChatFormat.scope)
public interface ChatFormatRepository extends MongoRepository<ChatFormat, String> {

}
