package fr.guiguilechat.jcelechat.libs.spring.connect.character.informations;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.corporation.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.connect.resolve.IdResolution;
import fr.guiguilechat.jcelechat.libs.spring.connect.resolve.IdResolutionService.IdResolutionListener;
import fr.guiguilechat.jcelechat.libs.spring.templates.services.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_characters_affiliation;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterAffiliationService
    extends
    ARemoteFetchedResourceService<CharacterAffiliation, Integer, R_post_characters_affiliation, CharacterAffiliationRepository>
    implements IdResolutionListener {

	@Lazy
	CorporationInfoService corporationInfoService;

	// auto management

	@Override
	protected CharacterAffiliation create(Integer RemoteId) {
		CharacterAffiliation ret = new CharacterAffiliation();
		ret.setRemoteId(RemoteId);
		return ret;
	}

	@Override
	protected Requested<R_post_characters_affiliation> fetchData(Integer RemoteId,
	    Map<String, String> properties) {
		Requested<R_post_characters_affiliation[]> ret = ESIRawPublic.INSTANCE.post_affiliation(new int[] { RemoteId },
		    properties);
		return ret.mapBody(arr -> arr[0]);
	}

	// batch update

	private int maxSimultFetch = 1000;

	@Getter
	private final boolean supportsBatchUpdate = true;

	@Override
	public Map<CharacterAffiliation, CompletableFuture<CharacterAffiliation>> batchUpdate(
	    List<CharacterAffiliation> data) {
		log.debug(" updating list of {} elements service {}", data.size(), getClass().getSimpleName());
		if (data == null || data.isEmpty()) {
			return Map.of();
		}
		for (int i = 0; i < data.size(); i += maxSimultFetch) {
			List<? extends CharacterAffiliation> subData = data.subList(i, Math.min(data.size(), i + maxSimultFetch));
			// System.err.println("fetching next " + subData.size() + " ids for character
			// affiliation");
			int[] charIds = subData.stream().mapToInt(CharacterAffiliation::getRemoteId).toArray();
			Requested<R_post_characters_affiliation[]> response = ESIRawPublic.INSTANCE.post_affiliation(charIds, null);
			int responseCode = response.getResponseCode();
			switch (responseCode) {
			case 200:
				Map<Integer, R_post_characters_affiliation> retMap = Stream.of(response.getOK())
				    .collect(Collectors.toMap(r -> r.character_id, r -> r));
				for (CharacterAffiliation caf : subData) {
					if (retMap.containsKey(caf.getRemoteId())) {
						caf.update(retMap.get(caf.getRemoteId()));
						updateMetaOk(caf, response);
						save(caf);
						log.trace(
						    "saved new affiliation for character " + caf.getRemoteId() + " , expires at " + caf.getExpires());
					} else {
						log.error(
						    "fetched character affiliation for " + caf.getRemoteId() + " but got ids for " + retMap.keySet());
					}
				}
				break;
			default:
				log.error("while updating affiliations, received response code {} and error {}", responseCode,
				    response.getError());
			}
		}
		return Map.of();
	}

	@Override
	public void onNewIdResolution(IdResolution idResolution) {
		if (idResolution.getCategory() == post_universe_names_category.character) {
			createIfAbsent(idResolution.getRemoteId(), false);
		}
	}

}