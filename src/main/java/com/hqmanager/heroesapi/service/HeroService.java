package com.hqmanager.heroesapi.service;

import com.hqmanager.heroesapi.document.Hero;
import com.hqmanager.heroesapi.repository.HeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class HeroService {
    private final HeroRepository heroRepository;

    public Flux<Hero> findAll() {
        return Flux.fromIterable(this.heroRepository.findAll());
    }

    public Mono<Hero> findByIdHero(String id) {
        return Mono.justOrEmpty(this.heroRepository.findById(id));
    }

    public Mono<Hero> save(Hero hero) {
        return Mono.justOrEmpty(this.heroRepository.save(hero));
    }

    public Mono<Boolean> deleteByIdHero(String id) {
        heroRepository.deleteById(id);
        return Mono.just(true);
    }

}
