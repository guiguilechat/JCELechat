package fr.guiguilechat.jcelechat.libs.spring.npc.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.Faction;
import fr.guiguilechat.jcelechat.libs.spring.npc.repositories.FactionRepository;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_factions;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FactionService {

	private final FactionRepository repo;

	String lastEtag = null;
	Instant expires = Instant.now();

	@Transactional
	@Scheduled(fixedRateString = "${esinpc.factionupdater.fetchperiod:3600000}", initialDelayString = "${esinpc.factionupdater.fetchdelay:0000}")
	protected void updateFactions() {
		if (Instant.now().isBefore(expires)) {
			return;
		}
		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}

		try {
			Requested<R_get_universe_factions[]> response = ESIRawPublic.INSTANCE.get_universe_factions(properties);
			int responseCode = response.getResponseCode();
			switch (responseCode) {
			case 200:
				updateFromFetched(response.getOK());
				lastEtag = response.getETag();
				expires = response.getExpiresInstant();
				break;
			case 304:
				expires = response.getExpiresInstant();
				break;
			default:
				log.error("while updating factions received code {} and error {}", responseCode, response.getError());
			}
		} catch (Exception e) {
			log.error("while updating factions", e);
		}

	}

	protected void updateFromFetched(R_get_universe_factions[] fetched) {
		Map<Integer, Faction> existing = repo.findAll().stream().collect(Collectors.toMap(Faction::getFactionId, f -> f));
		List<Faction> created = new ArrayList<>();
		for (R_get_universe_factions uf : fetched) {
			int id = uf.faction_id;
			Faction f = existing.get(id);
			if (f == null) {
				f = new Faction();
				f.setFactionId(id);
				f.update(uf);
				created.add(f);
			} else {
				f.update(uf);
			}
		}
		repo.saveAll(existing.values());
		repo.saveAll(created);
	}

	public boolean fetched() {
		return lastEtag != null;
	}

	public Optional<Faction> forId(int factionId) {
		return repo.findById(factionId);
	}

	public List<Faction> forIds(List<Integer> factionIds) {
		return repo.findAllById(factionIds);
	}

	public Map<Integer, Faction> allById() {
		return repo.findAll().stream().collect(Collectors.toMap(Faction::getFactionId, f -> f));
	}

}
