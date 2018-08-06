package colligence.io.persistence.repository;

import colligence.io.persistence.model.MongoCache;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MongoCacheRepository extends MongoRepository<MongoCache, Long> {
}
