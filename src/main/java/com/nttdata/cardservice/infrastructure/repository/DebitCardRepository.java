package com.nttdata.cardservice.infrastructure.repository;

import com.nttdata.cardservice.infrastructure.document.DebitCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitCardRepository extends ReactiveMongoRepository<DebitCard, String> {
}
