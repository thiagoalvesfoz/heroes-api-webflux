package com.hqmanager.heroesapi;

import com.hqmanager.heroesapi.document.Hero;
import com.hqmanager.heroesapi.repository.HeroRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.hqmanager.heroesapi.constans.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesapiApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    HeroRepository heroRepository;


    @Test
    public void getOneHeroById(){

        Hero toSave = new Hero("10", "Wonder Woman", "dc comics", 2);
        heroRepository.save(toSave);

        webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),toSave.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("id", toSave.getId());


    }

    @Test
    public void getOneHeroNotFound(){

        webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"99")
                .exchange()
                .expectStatus().isNotFound();

    }


    @Test
    public void deleteHero(){

        Hero toDelete = new Hero("10", "Wonder Woman", "dc comics", 2);
        heroRepository.save(toDelete);

        webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),toDelete.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent()
                .expectBody(Void.class);

    }

}
