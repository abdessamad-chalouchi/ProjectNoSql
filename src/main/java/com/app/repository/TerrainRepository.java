package com.app.repository;

import com.app.entities.Terrain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface TerrainRepository extends MongoRepository<Terrain, String> {
    Optional<Terrain> findByCode(String code);
}
