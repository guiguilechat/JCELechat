package fr.guiguilechat.jcelechat.libs.spring.resolve;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.services.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_universe_names;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class IdResolutionService
    extends ARemoteFetchedResourceService<IdResolution, Integer, R_post_universe_names, IdResolutionRepository> {

	@Lazy
	private final Optional<List<IdResolutionListener>> idResolutionListeners;

	protected void oneNewIdResolution(IdResolution newIdResolution) {
		if(idResolutionListeners.isPresent()) {
			for( IdResolutionListener l : idResolutionListeners.get()) {
				l.onNewIdResolution(newIdResolution);
			}
		}
	}

	@Override
	protected IdResolution create(Integer entityId) {
		IdResolution ret = new IdResolution();
		ret.setRemoteId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_post_universe_names> fetchData(Integer id, Map<String, String> properties) {
		Requested<R_post_universe_names[]> ret = ESIRawPublic.INSTANCE.post_universe_names(new int[] { id },
		    properties);
		return ret.mapBody(arr -> arr[0]);
	}

	@Override
	public void updateMetaOk(IdResolution data, Requested<?> response) {
		data.setFetchActive(false);
		super.updateMetaOk(data, response);
	}

	// batch update

	private int maxSimultFetch = 1000;

	@Getter
	private final boolean supportsBatchUpdate = true;

	@Override
	public Map<IdResolution, CompletableFuture<IdResolution>> batchUpdate(List<IdResolution> data) {
		log.debug(" updating list of {} elements service {}", data.size(), getClass().getSimpleName());
		if (data == null || data.isEmpty()) {
			return Map.of();
		}
		for (int i = 0; i < data.size(); i += maxSimultFetch) {
			List<? extends IdResolution> subData = data.subList(i, Math.min(data.size(), i + maxSimultFetch));
			int[] elementsIds = subData.stream().mapToInt(IdResolution::getRemoteId).toArray();
			Requested<R_post_universe_names[]> response = ESIRawPublic.INSTANCE.post_universe_names(elementsIds, null);
			int responseCode = response.getResponseCode();
			switch (responseCode) {
			case 200:
				Map<Integer, R_post_universe_names> retMapById = Stream.of(response.getOK())
				    .collect(Collectors.toMap(r -> r.id, r -> r));
				for (IdResolution idr : subData) {
					if (retMapById.containsKey(idr.getRemoteId())) {
						idr.update(retMapById.get(idr.getRemoteId()));
						updateMetaOk(idr, response);
						save(idr);
					}
				}
				break;
			default:
				log.error("while resolving ids, received response code {} and error {}", responseCode,
				    response.getError());
				for (IdResolution idr : subData) {
					idr.increaseSuccessiveErrors();
					idr.setExpiresInRandom(idr.getSuccessiveErrors() * 60);
				}
			}
		}
		return Map.of();
	}

	// service usage

	public String name(int id) {
		Optional<IdResolution> opt = repo().findById(id);
		if (opt != null && opt.isPresent()) {
			IdResolution data = opt.get();
			if (data != null && data.isFetched() && data.getName() != null) {
				return data.getName();
			}
		}
		return "unresolved:" + id;
	}

}
