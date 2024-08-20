package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Limit;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;

public interface ContractInfoRepository extends IRemoteEntityRepository<ContractInfo, Integer> {

	public List<ContractInfo> findByRegionIdAndRemovedFalseAndFetchedTrue(int regionId);

	public Stream<ContractInfo> findByRemovedFalseAndFetchedTrueAndType(get_contracts_public_region_id_type ptype);

	public Stream<ContractInfo> findByRemovedFalseAndFetchedTrueAndOffersOneTypeForIskTrueAndType(
	    get_contracts_public_region_id_type ptype);

	public List<ContractInfo> findByRegionInAndRemovedFalse(Iterable<ContractRegion> regions);

	public List<ContractInfo> findByFetchActiveTrueAndRemovedFalseAndExpiresLessThanOrderByExpiresAsc(Instant now,
	    Limit limit);


}
