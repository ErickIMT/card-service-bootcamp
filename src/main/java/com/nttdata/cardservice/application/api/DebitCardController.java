package com.nttdata.cardservice.application.api;

import com.nttdata.cardservice.domain.dto.DebitResDTO;
import com.nttdata.cardservice.infrastructure.document.DebitCard;
import com.nttdata.cardservice.infrastructure.service.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/debitcards")
public class DebitCardController {
  @Autowired
  private DebitCardService debitCardService;

  @GetMapping
  private Flux<DebitCard> getAllDebitCards() {
    return debitCardService.getAll();
  }

  @GetMapping("/{id}")
  private Mono<DebitCard> getAllDebitCards(@PathVariable("id") String id) {
    return debitCardService.getDebitCardById(id);
  }

  @PostMapping
  private Mono<DebitCard> saveDebitCard(@RequestBody DebitCard debitCard) {
    return debitCardService.saveDebitCard(debitCard);
  }

  @GetMapping("/info/{id}")
  private Mono<DebitResDTO> getLastTransactions(String id){
    return debitCardService.getLastTransactions(id);
  }
}
