package fr.guiguilechat.jcelechat.libs.spring.npc.corporation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdateListener;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EnpcCorporations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
public class CorporationUpdaterService implements SdeUpdateListener {

	@Lazy
	private final CorporationInfoService corporationInfoService;

	@Lazy
	private final LPExchangeService lpExchangeService;

	@Lazy
	private final NPCCorpService npcCorpService;

	@Lazy
	private final SeededService seededService;

	@Lazy
	private final TypeService typeService;

	private boolean sdeFileMissing = true;

	@Override
	public void beforeSdeUpdate() {
		sdeFileMissing = true;
		lpExchangeService.clear();
		npcCorpService.clear();
		seededService.clear();
	}

	static final Pattern ENTRYNAME_CORPORATIONS_PATTERN = Pattern.compile(
			"fsd/npcCorporations\\.yaml");

	@Override
	public void onSdeFile(String name, Supplier<InputStream> fileContent) {
		if (ENTRYNAME_CORPORATIONS_PATTERN.matcher(name).matches()) {
			saveCorporations(fileContent.get());
			return;
		}
	}

	protected void saveCorporations(InputStream inputStream) {
		sdeFileMissing = false;
		LinkedHashMap<Integer, EnpcCorporations> corporations = EnpcCorporations.LOADER.from(inputStream);
		Set<Integer> allCorpIds = Stream.concat(corporations.keySet().stream(),
				corporations.values().stream().flatMap(c -> c.exchangeRates.keySet().stream())).collect(Collectors.toSet());
		Map<Integer, CorporationInfo> id2CorporationInfo = corporationInfoService
		    .createIfAbsent(new ArrayList<>(allCorpIds));
		Set<Integer> allTypeIds = corporations.values().stream().flatMap(c -> c.corporationTrades.keySet().stream())
				.collect(Collectors.toSet());
		Map<Integer, Type> id2Type = typeService.createIfAbsent(new ArrayList<>(allTypeIds));
		List<LPExchange> lpExchanges = new ArrayList<>();
		List<NPCCorp> npcCorps = new ArrayList<>();
		List<Seeded> seededs = new ArrayList<>();

		for (Entry<Integer, EnpcCorporations> e : corporations.entrySet()) {
			EnpcCorporations entry = e.getValue();
			CorporationInfo ci = id2CorporationInfo.get(e.getKey());
			NPCCorp npcCorp = NPCCorp.of(ci, entry);
			npcCorps.add(npcCorp);

			for (Entry<Integer, Double> e2 : entry.corporationTrades.entrySet()) {
				Type type = id2Type.get(e2.getKey());
				seededs.add(
						Seeded.builder()
						.seeder(ci)
						.type(type)
						.value(e2.getValue())
						.build());
			}

			for (Entry<Integer, Double> e2 : entry.exchangeRates.entrySet()) {
				CorporationInfo target = id2CorporationInfo.get(e2.getKey());
				lpExchanges.add(LPExchange.builder()
						.owned(ci)
						.rate(e2.getValue())
						.target(target)
						.build());
			}
		}

		lpExchangeService.saveAll(lpExchanges);
		npcCorpService.saveAll(npcCorps);
		seededService.saveAll(seededs);
		log.info("udpated {} NPC corporations with {} lp exchanges and {} seeds", npcCorps.size(), lpExchanges.size(),
				seededs.size());
	}

	@Override
	public void afterSdeUpdate() {
		if (sdeFileMissing) {
			log.warn("service " + getClass().getSimpleName() + " did not receive file for matcher "
					+ ENTRYNAME_CORPORATIONS_PATTERN);
		}
	}

}
