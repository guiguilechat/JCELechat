package fr.guiguilechat.jcelechat.libs.spring.connect.services;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.model.AFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.connect.repositories.IFetchedResourceRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Getter
public abstract class AFetchedResourceService<Entity extends AFetchedResource<Id, Fetched>, Id, Fetched, Repository extends IFetchedResourceRepository<Entity, Id>> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	private Repository repo;

	public Entity save(Entity data) {
		if (data.getExpires() == null) {
			data.setExpires(Instant.now());
		}
		if (data.getCreated() == null) {
			data.setCreated(Instant.now());
		}
		data.setLastUpdate(Instant.now());
		return repo().save(data);
	}

	protected boolean isActivateNewEntry() {
		return true;
	}

	protected abstract Entity create(int characterId);

	protected abstract Requested<Fetched> fetchData(Id id, Map<String, String> properties);

	protected Optional<Entity> forId(Id id) {
		return repo().findById(id);
	}

	public Optional<Entity> getFetched(Id id) {
		Optional<Entity> ret = forId(id);
		if (ret.isEmpty() || ret.get().isFetched() || !ret.get().isActive()) {
			return ret;
		}
		try {
			update(ret.get()).get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("while fetching " + ret.get().getClass() + " for data id" + id, e);
		}
		return ret;
	}

	@Transactional
	@Async
	public CompletableFuture<Void> update(Entity data) {
		String lastEtag = data.getLastEtag();
		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		try {
			Requested<Fetched> response = fetchData(data.getRemoteId(), properties);
			int responseCode = response.getResponseCode();
			switch (responseCode) {
			case 200:
				data.updateMeta(response.getExpiresInstant(), response.getETag());
				data.update(response.getOK());
				data = save(data);
				System.err.println(" updated data " + data.getClass().getSimpleName() + " for id " + data.getRemoteId()
				    + " with expires=" + data.getExpires());
				break;
			case 304:
				data.setExpires(response.getExpiresInstant());
				data = save(data);
				break;
			default:
				log.error("while updating data remoteid {} info class {}, received response code {} and error {}",
				    data.getRemoteId(), data.getClass().getSimpleName(), responseCode, response.getError());
			}
		} catch (Exception e) {
			log.error("while updating " + data.getClass().getSimpleName() + " for data remoteid " + data.getRemoteId(), e);
		}
		return CompletableFuture.completedFuture(null);
	}

	public int getMaxUpdates() {
		return 1000;
	}

	public List<Entity> listForUpdate() {
		return repo().findTop1000ByActiveTrueAndExpiresLessThan(Instant.now());
	}

	public List<CompletableFuture<Void>> update(List<Entity> data) {
		log.debug(" updating list of {} elements service {}", data.size(), getClass().getSimpleName());
		return data.parallelStream().limit(getMaxUpdates()).map(this::update).toList();
	}

	public List<CompletableFuture<Void>> update() {
		return update(listForUpdate());
	}
}
