package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.services.SourceType;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.services.SchemProductService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.factories.P4Launchpad;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Service
public class PlanetEvalService {

	@Autowired
	private SchemProductService schemProductService;


	@Autowired
	private TypeService typeService;

	public static record ConsumeProduct(Map<Integer, Long> consume, Map<Integer, Long> product) {
	}

	public static interface PlanetaryFactory {

		public ConsumeProduct production(int hours);

	}

	@Getter
	@Setter
	@ToString
	public static class EvalParams {

		private double brpct = 2.0;
		private int customCodeExpertise = 5;
		private boolean hs = false;
		private double margin = 5.0;
		private int nbPlanets = 1;
		private long seconds = 4 * 24 * 3600;
		private SourceType sourcing = SourceType.sobo;
		private double taxpct = 3.6;
		private double volumicPrice = 100.0;

	}

	public static final int P4_GID = 1041;
	public static final int P3_GID = 1040;
	public static final int P2_GID = 1034;
	public static final int P1_GID = 1042;
	public static final int P0_CID = 42;

	public Stream<PlanetaryFactory> streamFactories() {
		return Stream.concat(
				Stream.empty(),
				schemProductService.producers(typeService.byGroupId(P4_GID)).stream().map(P4Launchpad::new)
		);
	}

}
