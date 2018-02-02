package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.AgentsInSpace;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.AsteroidBelt;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.AuditLogSecureContainer;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Beacon;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Biomass;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.CargoContainer;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Cloud;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Comet;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Constellation;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.ConstructionPlatform;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.CosmicAnomaly;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.CosmicSignature;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.CovertBeacon;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.DisruptableStationServices;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.EffectBeacon;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.EntosisCommandNode;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.ForceField;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.FreightContainer;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.GlobalWarpDisruptor;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.HarvestableCloud;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Landmark;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.LargeCollidableObject;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.MassiveEnvironments;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.MobileSentryGun;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Moon;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.MoonChunk;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.MoonMiningBeacon;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.OrbitalTarget;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Planet;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.PlanetaryCloud;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Region;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Ring;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Satellite;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.SecondarySun;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.SecureCargoContainer;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.ShippingCrates;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.SolarSystem;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Stargate;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.StationImprovementPlatform;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.StationUpgradePlatform;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Sun;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.SuperWeaponBeacon;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.WarpGate;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Wormhole;
import fr.guiguilechat.eveonline.model.sde.items.types.celestial.Wreck;

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
        return Stream.of(Sun.load(), Biomass.load(), StationUpgradePlatform.load(), DisruptableStationServices.load(), SuperWeaponBeacon.load(), HarvestableCloud.load(), MoonChunk.load(), Ring.load(), PlanetaryCloud.load(), CosmicAnomaly.load(), EffectBeacon.load(), WarpGate.load(), Wreck.load(), SecureCargoContainer.load(), Stargate.load(), Comet.load(), Region.load(), Constellation.load(), AsteroidBelt.load(), AgentsInSpace.load(), LargeCollidableObject.load(), Wormhole.load(), Moon.load(), CosmicSignature.load(), SolarSystem.load(), Planet.load(), Cloud.load(), AuditLogSecureContainer.load(), OrbitalTarget.load(), GlobalWarpDisruptor.load(), FreightContainer.load(), Beacon.load(), ShippingCrates.load(), MobileSentryGun.load(), ForceField.load(), MoonMiningBeacon.load(), ConstructionPlatform.load(), EntosisCommandNode.load(), MassiveEnvironments.load(), CargoContainer.load(), SecondarySun.load(), Satellite.load(), Landmark.load(), StationImprovementPlatform.load(), CovertBeacon.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
