package fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {

	public List<Region> findByUniverse(String universe);

	public List<Region> findByNameEqualsIgnoreCase(String name);

}
