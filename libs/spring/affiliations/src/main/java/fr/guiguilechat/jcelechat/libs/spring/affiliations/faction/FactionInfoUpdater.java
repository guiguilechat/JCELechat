package fr.guiguilechat.jcelechat.libs.spring.affiliations.faction;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_factions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class FactionInfoUpdater {

	private final FactionInfoService factionInfoService;

	@Value("${esi.affiliations.faction.updater.skip:false}")
	private boolean skipUpdate = false;

	private String lastEtag = null;
	private Instant expires = Instant.now();

	@Scheduled(fixedRateString = "${esi.affiliations.faction.updater.cycle:3600000}", fixedDelayString = "${esi.affiliations.faction.updater.delay:0}")
	protected void updateFactions() {
		if (skipUpdate || expires.isAfter(Instant.now())) {
			return;
		}
		Map<Integer, FactionInfo> existing = factionInfoService.listAll();
		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		Requested<R_get_universe_factions[]> response = ESIRawPublic.INSTANCE.get_universe_factions(properties);
		if (response != null) {
			switch (response.getResponseCode()) {
			case 304:
				expires = response.getExpiresInstant();
				return;
			case 200:
				R_get_universe_factions[] newValues = response.getOK();
				log.info("updating faction with new " + newValues + " elements");
				for (R_get_universe_factions factionData : newValues) {
					FactionInfo found = existing.get(factionData.faction_id);
					if (found == null) {
						found = new FactionInfo();
						found.setFactionId(factionData.faction_id);
					}
					found.update(factionData);
					factionInfoService.save(found);
				}
				return;
			default:
				log.warn("while fetching factions code={}, error={}", response.getResponseCode(), response.getError());
			}
		}
	}
}
