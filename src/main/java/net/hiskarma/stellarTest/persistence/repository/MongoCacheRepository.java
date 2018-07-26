package net.hiskarma.stellarTest.persistence.repository;

import net.hiskarma.stellarTest.persistence.model.MongoCache;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoCacheRepository extends MongoRepository<MongoCache, Long> {
//    int updateCache(String mongocache, boolean value);
}
