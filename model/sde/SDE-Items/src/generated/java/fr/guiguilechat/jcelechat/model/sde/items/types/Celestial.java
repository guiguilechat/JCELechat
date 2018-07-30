package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.AbyssalEnvironment;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.AbyssalHazards;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.AbyssalTrace;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.AgentsInSpace;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.AsteroidBelt;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.AuditLogSecureContainer;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Beacon;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Biomass;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.CargoContainer;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Cloud;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Comet;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Constellation;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.ConstructionPlatform;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.CosmicAnomaly;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.CosmicSignature;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.CovertBeacon;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.DisruptableStationServices;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.EffectBeacon;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.EntosisCommandNode;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.ForceField;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.FreightContainer;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.GlobalWarpDisruptor;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.HarvestableCloud;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.IndustrialSupportFacility;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Landmark;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.LargeCollidableObject;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Locators;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.MassiveEnvironments;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.MobileSentryGun;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Moon;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.MoonChunk;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.MoonMiningBeacon;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.NonInteractableObject;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.NonScalableClouds;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.OrbitalTarget;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Planet;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.PlanetaryCloud;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Region;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Ring;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Satellite;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.SecondarySun;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.SecureCargoContainer;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.ShippingCrates;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Stargate;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.StationConversionMonuments;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.StationImprovementPlatform;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.StationUpgradePlatform;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Sun;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.SuperWeaponBeacon;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.TriglavianSupportPylons;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.WarpGate;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Wormhole;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Wreck;

public abstract class Celestial
    extends Item
{

    @Override
    public int getCategoryId() {
        return  2;
    }

    @Override
    public Class<?> getCategory() {
        return Celestial.class;
    }

    public static Map<String, ? extends Celestial> loadCategory() {
        return Stream.of(AbyssalEnvironment.load(), AbyssalHazards.load(), AbyssalTrace.load(), AgentsInSpace.load(), AsteroidBelt.load(), AuditLogSecureContainer.load(), Beacon.load(), Biomass.load(), CargoContainer.load(), Cloud.load(), Comet.load(), Constellation.load(), ConstructionPlatform.load(), CosmicAnomaly.load(), CosmicSignature.load(), CovertBeacon.load(), DisruptableStationServices.load(), EffectBeacon.load(), EntosisCommandNode.load(), ForceField.load(), FreightContainer.load(), GlobalWarpDisruptor.load(), HarvestableCloud.load(), IndustrialSupportFacility.load(), Landmark.load(), LargeCollidableObject.load(), Locators.load(), MassiveEnvironments.load(), MobileSentryGun.load(), Moon.load(), MoonChunk.load(), MoonMiningBeacon.load(), NonInteractableObject.load(), NonScalableClouds.load(), OrbitalTarget.load(), Planet.load(), PlanetaryCloud.load(), Region.load(), Ring.load(), Satellite.load(), SecondarySun.load(), SecureCargoContainer.load(), ShippingCrates.load(), SolarSystem.load(), Stargate.load(), StationConversionMonuments.load(), StationImprovementPlatform.load(), StationUpgradePlatform.load(), Sun.load(), SuperWeaponBeacon.load(), TriglavianSupportPylons.load(), WarpGate.load(), Wormhole.load(), Wreck.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
