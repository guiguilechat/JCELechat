package fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.anon.character.information.CharacterInformation;
import fr.guiguilechat.jcelechat.libs.spring.anon.character.information.CharacterInformationService;
import fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.StationService;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.DiscoveringRemoteNumberEntityUpdater;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolutionService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_region_id;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.trade.contract.region")
public class ContractRegionUpdater extends
		DiscoveringRemoteNumberEntityUpdater<ContractRegion, Integer, List<R_get_contracts_public_region_id>, ContractRegionRepository, ContractRegionService> {

	@Lazy
	private final CharacterInformationService characterInformationService;

	@Lazy
	private final ContractInfoService contractInfoService;

	@Lazy
	private final CorporationInfoService corporationInfoService;

	@Lazy
	private final IdResolutionService idResolutionService;

	@Lazy
	private final StationService stationService;

	@Override
	protected Requested<List<R_get_contracts_public_region_id>> fetchData(Integer id, Map<String, String> properties) {
		Requested<List<R_get_contracts_public_region_id>> ret = ESIRawPublic.INSTANCE
				.requestGetPages((p, props) -> ESIRawPublic.INSTANCE.get_contracts_public(p,
						id, props), properties);
		return ret;
	}

	@Override
	protected void updateResponseOk(Map<ContractRegion, List<R_get_contracts_public_region_id>> responseOk) {
		super.updateResponseOk(responseOk);
		Map<ContractRegion, Map<Integer, ContractInfo>> regionToIdToContract = contractInfoService
				.presentByRegion(responseOk.keySet());
		List<ContractInfo> updated = new ArrayList<>();
		for (Entry<ContractRegion, List<R_get_contracts_public_region_id>> e : responseOk.entrySet()) {
			updated.addAll(
					createForRegion(
							e.getKey(),
							e.getValue(),
							regionToIdToContract.getOrDefault(e.getKey(), Map.of())));
		}
		log.trace("saving {} updated contract infos", updated.size());
		contractInfoService.saveAll(updated);
	}

	/**
	 * update if needed, and create the new contracts to save later
	 *
	 * @param region    region we fetched the contracts for
	 * @param contracts list of corresponding contracts
	 * @return list of contract info that have been modified (created or marked as
	 *         removed)
	 */
	protected List<ContractInfo> createForRegion(ContractRegion region,
			List<R_get_contracts_public_region_id> contracts,
			Map<Integer, ContractInfo> existingPresent) {
		log.debug(" contracts list {}({}) : received {}, stored active {}", region.getRegion().getName(),
				region.getId(),
				contracts.size(),
				existingPresent.size());

		// contract stored that are no more present are marked as removed and to be
		// fetched again, to find out if they are completed (403) or canceled (404)
		Map<Integer, R_get_contracts_public_region_id> newById = contracts.stream()
				.collect(Collectors.toMap(c -> c.contract_id, c -> c));
		List<ContractInfo> removed = new ArrayList<>();
		for (Entry<Integer, ContractInfo> e : existingPresent.entrySet()) {
			if (!newById.containsKey(e.getKey())) {
				e.getValue().setRemoved(true);
				e.getValue().setFetchActive(true);
				e.getValue().setRemovedBefore(Instant.now());
				removed.add(e.getValue());
			}
		}

		long preFetchStations = System.currentTimeMillis();
		Map<Long, SolarSystem> stationId2SolarSystem = stationService.getSolarSystems(contracts.stream()
				.filter(c -> c.start_location_id < Integer.MAX_VALUE)
				.map(c -> (int) c.start_location_id)
				.distinct().toList());
		long postFetchStations = System.currentTimeMillis();
		log.trace(" retrieved {} stations's solar systems in {} ms",
				stationId2SolarSystem.size(),
				postFetchStations - preFetchStations);
		ArrayList<ContractInfo> ret = new ArrayList<>(removed);

		// then create the new ones
		List<Integer> referencedCharacterIds = contracts.stream()
				.map(ci -> ci.issuer_id)
				.distinct().toList();
		log.trace(" getting {} issuers for {} new received contracts",
				referencedCharacterIds.size(),
				contracts.size());
		Function<Integer, CharacterInformation> getCharacter = characterInformationService
				.getOrCreate(referencedCharacterIds)::get;

		List<Integer> referencedCorporationIds = contracts.stream()
				.map(ci -> ci.issuer_corporation_id)
				.distinct().toList();
		log.trace("getting {} issuer corporations for {} new received contracts",
				referencedCorporationIds.size(),
				contracts.size());
		Function<Integer, CorporationInfo> getCorporation = corporationInfoService
				.getOrCreate(referencedCorporationIds)::get;

		List<ContractInfo> newContracts = contracts.stream()
				.filter(c -> !existingPresent.containsKey(c.contract_id))
				.map(c -> contractInfoService.createMinimal(c.contract_id)
						.updateContract(
								region,
								c,
								getCharacter,
								getCorporation)
						.updateSystem(stationId2SolarSystem))
				.toList();
		idResolutionService.createMissing(
				Stream.concat(referencedCharacterIds.stream(), referencedCorporationIds.stream()));
		log.debug("  contract list in {}({}) : {} new, {} removed",
				region.getRegion().getName(),
				region.getId(),
				newContracts.size(),
				removed.size());
		ret.addAll(newContracts);
		return ret;
	}

	@Override
	protected Requested<List<Integer>> discoverRemoteIds(Map<String, String> p) {
		return ESIRawPublic.INSTANCE.get_universe_regions(p).mapBody(List::of);
	}

}
