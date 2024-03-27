package fr.guiguilechat.jcelechat.libs.spring.market.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionContract;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;

public interface RegionContractRepository extends JpaRepository<RegionContract, Long> {

	public List<RegionContract> findByRegionRegionIdAndFetchedFalseAndRemovedFalseAndType(int regionId,
			get_contracts_public_region_id_type type);

	public List<RegionContract> findByRegionRegionIdAndRemovedFalse(int regionId);
}
