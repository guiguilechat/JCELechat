package fr.guiguilechat.jcelechat.jcesi.disconnected.market;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.types.Accessories;
import fr.guiguilechat.jcelechat.model.sde.types.AncientRelics;
import fr.guiguilechat.jcelechat.model.sde.types.Apparel;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import fr.guiguilechat.jcelechat.model.sde.types.Decryptors;
import fr.guiguilechat.jcelechat.model.sde.types.Deployable;
import fr.guiguilechat.jcelechat.model.sde.types.Drone;
import fr.guiguilechat.jcelechat.model.sde.types.Fighter;
import fr.guiguilechat.jcelechat.model.sde.types.Implant;
import fr.guiguilechat.jcelechat.model.sde.types.InfrastructureUpgrades;
import fr.guiguilechat.jcelechat.model.sde.types.Material;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryCommodities;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryInteraction;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryResources;
import fr.guiguilechat.jcelechat.model.sde.types.Reaction;
import fr.guiguilechat.jcelechat.model.sde.types.SKINs;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import fr.guiguilechat.jcelechat.model.sde.types.SovereigntyStructures;
import fr.guiguilechat.jcelechat.model.sde.types.SpecialEditionAssets;
import fr.guiguilechat.jcelechat.model.sde.types.Starbase;
import fr.guiguilechat.jcelechat.model.sde.types.Structure;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;
import fr.guiguilechat.jcelechat.model.sde.types.Subsystem;
import fr.guiguilechat.tools.JFXTools;

public class ShowHighestTrade {

	public static void main(String[] args) {
		int region = 10000002;
		RegionalMarket market = ESIAccess.INSTANCE.markets.getMarket(region);
		// ESIStatic.INSTANCE.cache.markets.types(region);
		List<EveType> types = Stream
				.of(Accessories.METACAT, AncientRelics.METACAT, Apparel.METACAT, Asteroid.METACAT, Blueprint.METACAT,
						Charge.METACAT, Commodity.METACAT, Decryptors.METACAT, Deployable.METACAT, Drone.METACAT, Fighter.METACAT,
						Implant.METACAT, InfrastructureUpgrades.METACAT, Material.METACAT, Module.METACAT,
						PlanetaryCommodities.METACAT, PlanetaryInteraction.METACAT, PlanetaryResources.METACAT, Reaction.METACAT,
						Ship.METACAT, Skill.METACAT, SKINs.METACAT, SovereigntyStructures.METACAT, SpecialEditionAssets.METACAT,
						Starbase.METACAT, Structure.METACAT, StructureModule.METACAT, Subsystem.METACAT)
				.flatMap(cat -> cat.load().values().stream()).filter(t -> t != null && t.published && t.marketGroup != 0)
				.peek(t -> market.getHistory(t.id).monthly.getTotalValue()).collect(Collectors.toList());
		types.stream().map(t -> new TypeMarketdata(t, market))
		.sorted((t1, t2) -> Double.compare(t2.totalValue, t1.totalValue)).forEach(System.out::println);
	}

	private static class TypeMarketdata {

		public final EveType type;
		public final double totalValue;

		public TypeMarketdata(EveType type, RegionalMarket market) {
			this.type = type;
			totalValue = market.getHistory(type.id).quarterly.getTotalValue().get();
		}

		@Override
		public String toString() {
			return type.name + "\t" + JFXTools.formatPrice(totalValue / 3);
		}

	}

}
