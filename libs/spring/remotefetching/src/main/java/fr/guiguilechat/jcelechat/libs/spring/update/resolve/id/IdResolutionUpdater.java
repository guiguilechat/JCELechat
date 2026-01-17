package fr.guiguilechat.jcelechat.libs.spring.update.resolve.id;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.DeducedEntityUpdater;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_universe_names;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.resolve.name")
public class IdResolutionUpdater extends
		DeducedEntityUpdater<IdResolution, Integer, IdResolutionRepository, IdResolutionService> {

	@Lazy
	private final Optional<List<IdResolutionListener>> idResolutionListeners;

	@Override
	public long nbToUpdate() {
		return repo().countByNextFetchBefore(Instant.now());
	}

	@Override
	protected boolean fetchUpdate() {
		List<IdResolution> list = repo().findByNextFetchBeforeOrderByFetchPriorityDescNextFetchAsc(
				Instant.now(),
				Limit.of(1000));
		if (list == null || list.isEmpty()) {
			return false;
		}
		log.debug(" {} has {} ids to update", serviceName(), list.size());
		int[] elementsIds = list.stream().mapToInt(IdResolution::getId).toArray();
		Requested<R_post_universe_names[]> response = ESIRawPublic.INSTANCE.post_universe_names(elementsIds, null);
		Instant now = Instant.now();
		int responseCode = response.getResponseCode();
		switch (responseCode) {
		case 200:
			Map<Integer, R_post_universe_names> retMapById = Stream.of(
					response.getOK())
					.collect(Collectors.toMap(r -> r.id, r -> r));
			List<IdResolution> updated = new ArrayList<>();
			for (IdResolution idr : list) {
				R_post_universe_names result = retMapById.get(idr.getId());
				if (result != null) {
					idr.update(result);
					idr.setNextFetch(now.plusSeconds(getUpdate().getDelayUpdated()));
					updated.add(idr);
				} else {
					log.error(
							"fetched character affiliation for " + idr.getId() + " but got ids for "
									+ retMapById.keySet());
				}
			}
			if (!updated.isEmpty() && idResolutionListeners != null && idResolutionListeners.isPresent()) {
				for (IdResolutionListener idrl : idResolutionListeners.get()) {
					idrl.onNewIdResolutions(updated);
				}
			}
			break;
		default:
			log.error("while resolving ids, received response code {} and error {}", responseCode,
					response.getError());
			// spread out the requests to have the errored ones less likely with many other
			// ones.
			for (int i = 0; i < list.size(); i++) {
				IdResolution idr = list.get(i);
				idr.increaseSuccessiveErrors();
				idr.setFetchPriority(1);
				idr.setNextFetch(now.plusSeconds(i * idr.getSuccessiveErrors()));
			}
		}
		repo().saveAllAndFlush(list);
		return true;
	}
}
