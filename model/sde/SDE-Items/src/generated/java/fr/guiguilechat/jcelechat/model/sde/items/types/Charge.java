package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedArtilleryAmmo;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedAutocannonAmmo;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedBeamLaserCrystal;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedBlasterCharge;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedCruiseMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedExoticPlasmaCharge;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedHeavyAssaultMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedHeavyMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedLightMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedPulseLaserCrystal;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedRailgunCharge;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedRocket;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedTorpedo;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedXLCruiseMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AdvancedXLTorpedo;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.ArmorCommandBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AutoTargetingCruiseMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AutoTargetingHeavyMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.AutoTargetingLightMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.Bomb;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.BombECM;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.BombEnergy;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.CapacitorBoosterCharge;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.CompactXLTorpedo;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.CruiseMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.DefenderMissiles;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.ExoticPlasmaCharge;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.FestivalCharges;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.FlexArmorHardenerScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.FlexShieldHardenerScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.FrequencyCrystal;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.GuidanceDisruptionScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.HeavyAssaultMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.HeavyDefenderMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.HeavyMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.HybridCharge;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.InformationCommandBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.InterdictionProbe;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.LightMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.MercoxitMiningCrystal;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.Mine;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.MiningCrystal;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.MiningForemanBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.MissileGuidanceScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.Modifications;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.NaniteRepairPaste;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.ObsoleteProbes;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.OrbitalAssaultUnit;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.PlanetSatellites;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.ProjectileAmmo;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.Rocket;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.ScannerProbe;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.SensorBoosterScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.SensorDampenerScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.ShieldBoosterScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.ShieldCommandBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.SkirmishCommandBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.StructureAntiCapitalMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.StructureAntiSubcapitalMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.StructureECMScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.StructureFestivalCharges;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.StructureGuidedBomb;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.StructureResistanceSwitcherScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.StructureWarpDisruptorScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.SurveyProbe;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.Torpedo;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.TrackingDisruptionScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.TrackingScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.WarpDisruptionScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.XLCruiseMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.XLTorpedo;

public abstract class Charge
    extends Item
{

    @Override
    public int getCategoryId() {
        return  8;
    }

    @Override
    public Class<?> getCategory() {
        return Charge.class;
    }

    public static Map<String, ? extends Charge> loadCategory() {
        return Stream.of(AdvancedArtilleryAmmo.load(), AdvancedAutocannonAmmo.load(), AdvancedBeamLaserCrystal.load(), AdvancedBlasterCharge.load(), AdvancedCruiseMissile.load(), AdvancedExoticPlasmaCharge.load(), AdvancedHeavyAssaultMissile.load(), AdvancedHeavyMissile.load(), AdvancedLightMissile.load(), AdvancedPulseLaserCrystal.load(), AdvancedRailgunCharge.load(), AdvancedRocket.load(), AdvancedTorpedo.load(), AdvancedXLCruiseMissile.load(), AdvancedXLTorpedo.load(), ArmorCommandBurstCharges.load(), AutoTargetingCruiseMissile.load(), AutoTargetingHeavyMissile.load(), AutoTargetingLightMissile.load(), Bomb.load(), BombECM.load(), BombEnergy.load(), CapacitorBoosterCharge.load(), CompactXLTorpedo.load(), CruiseMissile.load(), DefenderMissiles.load(), ExoticPlasmaCharge.load(), FestivalCharges.load(), FlexArmorHardenerScript.load(), FlexShieldHardenerScript.load(), FrequencyCrystal.load(), GuidanceDisruptionScript.load(), HeavyAssaultMissile.load(), HeavyDefenderMissile.load(), HeavyMissile.load(), HybridCharge.load(), InformationCommandBurstCharges.load(), InterdictionProbe.load(), LightMissile.load(), MercoxitMiningCrystal.load(), Mine.load(), MiningCrystal.load(), MiningForemanBurstCharges.load(), MissileGuidanceScript.load(), Modifications.load(), NaniteRepairPaste.load(), ObsoleteProbes.load(), OrbitalAssaultUnit.load(), PlanetSatellites.load(), ProjectileAmmo.load(), Rocket.load(), ScannerProbe.load(), SensorBoosterScript.load(), SensorDampenerScript.load(), ShieldBoosterScript.load(), ShieldCommandBurstCharges.load(), SkirmishCommandBurstCharges.load(), StructureAntiCapitalMissile.load(), StructureAntiSubcapitalMissile.load(), StructureECMScript.load(), StructureFestivalCharges.load(), StructureGuidedBomb.load(), StructureResistanceSwitcherScript.load(), StructureWarpDisruptorScript.load(), SurveyProbe.load(), Torpedo.load(), TrackingDisruptionScript.load(), TrackingScript.load(), WarpDisruptionScript.load(), XLCruiseMissile.load(), XLTorpedo.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
