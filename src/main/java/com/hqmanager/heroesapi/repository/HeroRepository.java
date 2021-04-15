package com.hqmanager.heroesapi.repository;

import com.hqmanager.heroesapi.document.Hero;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HeroRepository extends CrudRepository<Hero, String> {
}
