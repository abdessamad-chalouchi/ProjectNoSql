package com.app.repository;

import com.app.entities.Carnet;
import com.app.entities.CarnetId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CarnetRepository extends MongoRepository<Carnet, CarnetId> {
}
