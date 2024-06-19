package fr.guiguilechat.jcelechat.libs.spring.remotefetching.resolve;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_universe_names;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.resolve.name")
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
		ret.setId(entityId);
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

	@Override
	protected Map<IdResolution, R_post_universe_names> fetchData(List<IdResolution> data) {
		log.debug(" updating list of {} elements service {}", data.size(), getClass().getSimpleName());
		if (data == null || data.isEmpty()) {
			return Map.of();
		}
		Map<IdResolution, R_post_universe_names> ret = new HashMap<>();
		int[] elementsIds = data.stream().mapToInt(IdResolution::getId).toArray();
			Requested<R_post_universe_names[]> response = ESIRawPublic.INSTANCE.post_universe_names(elementsIds, null);
			int responseCode = response.getResponseCode();
			switch (responseCode) {
			case 200:
				Map<Integer, R_post_universe_names> retMapById = Stream.of(response.getOK())
				    .collect(Collectors.toMap(r -> r.id, r -> r));
				for (IdResolution idr : data) {
					R_post_universe_names result = retMapById.get(idr.getId());
					if (result != null) {
						ret.put(idr, result);
					} else {
						log.error(
						    "fetched character affiliation for " + idr.getId() + " but got ids for " + retMapById.keySet());
						updateNullResponse(idr);
					}
				}
				break;
			default:
				log.error("while resolving ids, received response code {} and error {}", responseCode,
				    response.getError());
				for (IdResolution idr : data) {
					idr.increaseSuccessiveErrors();
					idr.setExpiresInRandom(idr.getSuccessiveErrors() * 60);
				}
			}
		return ret;

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
