package com.its.controller;

import com.its.entity.CardEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RestController
@Slf4j
public class CardClientController {

    private WebClient.Builder webClientBuilder;
    private final CardEntity fallBackCard = CardEntity
                                                .builder()
                                                .address("Dwarka")
                                                .cardNumber("535289756842")
                                                .country("India")
                                                .cvv("123")
                                                .expiryDate("1121")
                                                .issuingNetwork("Mastercard")
                                                .name("Krishna")
                                                .build();


    public CardClientController(WebClient.Builder webClientBuilder) {
        log.info("111 Instantiating WebClientBuilder within constructor of CardClientController ");
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("/cards")
    public Mono all() {
        log.info("Entering CardClientController : all");
        Flux cardFlux = webClientBuilder
                            .build()
                            .get()
                            //.uri("http://localhost:8091/cards")
                            .uri("http://cm4sb-card-service/cards")
                            .retrieve()
                            .bodyToFlux(CardEntity.class);

        log.info("Flux of card obtained from card-service application");
        return cardFlux
                .collectList();
                //.onErrorReturn(Collections.singletonList(fallBackCard));
    }

    @GetMapping("/card/{cardId}")
    public Mono<CardEntity> cardById(@PathVariable String cardId) {
        log.info("Entering CardClientController : cardById with path variable as {} ", cardId);
        return webClientBuilder
                .build()
                .get()
                //.uri("http://localhost:8091/card/{cardId}", cardId)
                .uri("http://cm4sb-card-service/card/{cardId}", cardId)
                .retrieve()
                .bodyToMono(CardEntity.class);
                //.onErrorReturn(fallBackCard);
    }
}
