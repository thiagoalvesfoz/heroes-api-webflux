package com.hqmanager.heroesapi.controller;

import com.hqmanager.heroesapi.document.Hero;
import com.hqmanager.heroesapi.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.hqmanager.heroesapi.constans.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RestController
@RequiredArgsConstructor
public class HeroController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HeroController.class);
    private final HeroService heroService;

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Hero> getAllItems() {
        log.info("requesting the list of all heroes");
        return heroService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    public Mono<ResponseEntity<Hero>> findByHero(@PathVariable String id) {
        log.info("requesting the hero with id {}", id);
        return heroService.findByIdHero(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Hero> createHero(@RequestBody Hero hero) {
        log.info("requesting a new hero to be create");
        return heroService.save(hero);
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL+"/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteByIdHero(@PathVariable String id) {
        log.info("deleting a hero with id {}", id);
        return heroService.deleteByIdHero(id).then();
    }


}
