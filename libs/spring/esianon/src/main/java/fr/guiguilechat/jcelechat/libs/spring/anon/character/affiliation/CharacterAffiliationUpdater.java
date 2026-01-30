package fr.guiguilechat.jcelechat.libs.spring.anon.character.affiliation;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.anon.alliance.information.AllianceInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.alliance.information.AllianceInfoService;
import fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.anon.faction.information.FactionInfoService;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityUpdater;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_characters_affiliation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.character.affiliation")
public class CharacterAffiliationUpdater extends
		RemoteNumberEntityUpdater<CharacterAffiliation, Integer, R_post_characters_affiliation, CharacterAffiliationRepository, CharacterAffiliationService> {

	@Lazy
	private final AllianceInfoService allianceInfoService;

	@Lazy
	private final CorporationInfoService corporationInfoService;

	@Lazy
	private final FactionInfoService factionInfoService;

	@Override
	protected Requested<R_post_characters_affiliation> fetchData(Integer Id,
			Map<String, String> properties) {
		Requested<R_post_characters_affiliation[]> ret = ESIRawPublic.INSTANCE.post_affiliation(new int[] { Id },
				properties);
		return ret.mapBody(arr -> arr[0]);
	}

	@Override
	public Map<CharacterAffiliation, R_post_characters_affiliation> fetchData(List<CharacterAffiliation> toUpdate) {
		log.debug(" updating list of {} elements service {}", toUpdate.size(), getClass().getSimpleName());
		if (toUpdate == null || toUpdate.isEmpty()) {
			return Map.of();
		}
		Map<CharacterAffiliation, R_post_characters_affiliation> ret = new HashMap<>();
		int[] charIds = toUpdate.stream().mapToInt(CharacterAffiliation::getId).toArray();
		Requested<R_post_characters_affiliation[]> response = ESIRawPublic.INSTANCE.post_affiliation(charIds, null);
		int responseCode = response.getResponseCode();
		switch (responseCode) {
		case 200:
			Map<Integer, R_post_characters_affiliation> retMap = Stream.of(response.getOK())
					.collect(Collectors.toMap(r -> r.character_id, r -> r));
			for (CharacterAffiliation caf : toUpdate) {
				R_post_characters_affiliation result = retMap.get(caf.getId());
				if (result != null) {
					ret.put(caf, result);
				} else {
					log.error(
							" fetched character affiliation for " + caf.getId() + " but got ids for "
									+ retMap.keySet());
					updateNullResponse(caf);
				}
			}
			break;
		default:
			log.error(" while updating affiliations, received response code {} and error {}", responseCode,
					response.getError());
			for (CharacterAffiliation ca : toUpdate) {
				ca.increaseSuccessiveErrors();
				ca.setExpiresInRandom(ca.getSuccessiveErrors() * 60);
			}
		}
		return ret;
	}

	protected void updateResponseOk(CharacterAffiliation data,
			R_post_characters_affiliation response,
			Map<Integer, AllianceInfo> idToAlliance,
			Map<Integer, CorporationInfo> idToCorporation) {
		data.setAlliance(idToAlliance.get(response.alliance_id));
		data.setCorporation(idToCorporation.get(response.corporation_id));
		data.setFaction(factionInfoService.getOrCreate(response.faction_id));
	}

	@Override
	protected void updateResponseOk(Map<CharacterAffiliation, R_post_characters_affiliation> responseOk) {
		super.updateResponseOk(responseOk);
		Instant nextFetch = Instant.now().plusSeconds(3600 * 12);
		Map<Integer, AllianceInfo> idToAlliance = allianceInfoService
				.getOrCreate(responseOk.values().stream()
						.mapToInt(r -> r.alliance_id)
						.distinct().filter(i -> i > 0)
						.boxed().toList());
		Map<Integer, CorporationInfo> idToCorporation = corporationInfoService
				.getOrCreate(responseOk.values().stream()
						.mapToInt(r -> r.corporation_id)
						.distinct().filter(i -> i > 0)
						.boxed().toList());
		responseOk.entrySet().stream()
				.forEach(e -> {
					updateResponseOk(e.getKey(), e.getValue(), idToAlliance, idToCorporation);
					e.getKey().setExpires(nextFetch);
				});
	}
}
