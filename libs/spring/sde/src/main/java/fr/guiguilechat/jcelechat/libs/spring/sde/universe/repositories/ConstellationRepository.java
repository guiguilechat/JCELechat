package fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;

public interface ConstellationRepository extends JpaRepository<Constellation, Integer> {

	public List<Constellation> findByRegion(Region region);

}
