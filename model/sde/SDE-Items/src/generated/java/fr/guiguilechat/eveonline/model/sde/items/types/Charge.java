package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AdvancedArtilleryAmmo;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AdvancedAutocannonAmmo;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AdvancedBeamLaserCrystal;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AdvancedBlasterCharge;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AdvancedCruiseMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AdvancedHeavyAssaultMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AdvancedHeavyMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AdvancedLightMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AdvancedPulseLaserCrystal;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AdvancedRailgunCharge;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AdvancedRocket;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AdvancedTorpedo;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AdvancedXLCruiseMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AdvancedXLTorpedo;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.ArmorCommandBurstCharges;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AutoTargetingCruiseMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AutoTargetingHeavyMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.AutoTargetingLightMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.Bomb;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.BombECM;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.BombEnergy;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.CapacitorBoosterCharge;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.CompactXLTorpedo;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.CruiseMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.DefenderMissiles;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.FestivalCharges;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.FlexArmorHardenerScript;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.FlexShieldHardenerScript;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.FrequencyCrystal;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.GuidanceDisruptionScript;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.HeavyAssaultMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.HeavyDefenderMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.HeavyMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.HybridCharge;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.InformationCommandBurstCharges;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.InterdictionProbe;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.LightMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.MercoxitMiningCrystal;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.Mine;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.MiningCrystal;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.MiningForemanBurstCharges;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.MissileGuidanceScript;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.Modifications;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.NaniteRepairPaste;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.ObsoleteProbes;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.OrbitalAssaultUnit;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.PlanetSatellites;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.ProjectileAmmo;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.Rocket;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.ScannerProbe;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.SensorBoosterScript;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.SensorDampenerScript;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.ShieldBoosterScript;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.ShieldCommandBurstCharges;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.SkirmishCommandBurstCharges;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.StructureAntiCapitalMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.StructureAntiSubcapitalMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.StructureECMScript;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.StructureGuidedBomb;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.StructureResistanceSwitcherScript;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.SurveyProbe;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.Torpedo;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.TrackingDisruptionScript;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.TrackingScript;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.WarpDisruptionScript;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.XLCruiseMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.charge.XLTorpedo;

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
        return Stream.of(FrequencyCrystal.load(), FestivalCharges.load(), Mine.load(), SensorBoosterScript.load(), InterdictionProbe.load(), AdvancedXLTorpedo.load(), Torpedo.load(), CruiseMissile.load(), AdvancedArtilleryAmmo.load(), AdvancedHeavyAssaultMissile.load(), CompactXLTorpedo.load(), Bomb.load(), ObsoleteProbes.load(), FlexArmorHardenerScript.load(), StructureResistanceSwitcherScript.load(), FlexShieldHardenerScript.load(), ScannerProbe.load(), SurveyProbe.load(), StructureECMScript.load(), ShieldBoosterScript.load(), AdvancedXLCruiseMissile.load(), StructureAntiSubcapitalMissile.load(), MissileGuidanceScript.load(), AutoTargetingCruiseMissile.load(), MiningCrystal.load(), NaniteRepairPaste.load(), AutoTargetingHeavyMissile.load(), InformationCommandBurstCharges.load(), XLTorpedo.load(), AdvancedAutocannonAmmo.load(), ProjectileAmmo.load(), CapacitorBoosterCharge.load(), TrackingDisruptionScript.load(), DefenderMissiles.load(), AdvancedPulseLaserCrystal.load(), StructureGuidedBomb.load(), AutoTargetingLightMissile.load(), XLCruiseMissile.load(), HybridCharge.load(), HeavyDefenderMissile.load(), PlanetSatellites.load(), BombEnergy.load(), LightMissile.load(), AdvancedLightMissile.load(), AdvancedRocket.load(), AdvancedBlasterCharge.load(), AdvancedHeavyMissile.load(), MiningForemanBurstCharges.load(), SkirmishCommandBurstCharges.load(), AdvancedCruiseMissile.load(), BombECM.load(), Rocket.load(), Modifications.load(), ShieldCommandBurstCharges.load(), OrbitalAssaultUnit.load(), HeavyMissile.load(), AdvancedBeamLaserCrystal.load(), TrackingScript.load(), ArmorCommandBurstCharges.load(), StructureAntiCapitalMissile.load(), MercoxitMiningCrystal.load(), WarpDisruptionScript.load(), AdvancedRailgunCharge.load(), HeavyAssaultMissile.load(), GuidanceDisruptionScript.load(), AdvancedTorpedo.load(), SensorDampenerScript.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
