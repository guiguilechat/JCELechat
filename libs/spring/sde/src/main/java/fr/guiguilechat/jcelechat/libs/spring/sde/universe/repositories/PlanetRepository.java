package fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Planet;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

}
