package com.nttdata.cardservice.infrastructure.service;

import com.nttdata.cardservice.domain.dto.DebitResDTO;
import com.nttdata.cardservice.infrastructure.document.DebitCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardService {

  Flux<DebitCard> getAll();
  Mono<DebitCard> getDebitCardById(String id);
  Mono<DebitCard> saveDebitCard(DebitCard debitCard);
  Mono<DebitResDTO> getLastTransactions(String id);
}
