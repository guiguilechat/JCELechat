package fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Constellation;

public interface ConstellationRepository extends JpaRepository<Constellation, Integer> {

}
