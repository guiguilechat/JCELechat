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
        return Stream.of(AdvancedArtilleryAmmo.load(), GuidanceDisruptionScript.load(), AdvancedHeavyMissile.load(), AdvancedBeamLaserCrystal.load(), ArmorCommandBurstCharges.load(), StructureGuidedBomb.load(), Bomb.load(), StructureAntiCapitalMissile.load(), MissileGuidanceScript.load(), ShieldBoosterScript.load(), AdvancedPulseLaserCrystal.load(), AdvancedLightMissile.load(), InterdictionProbe.load(), SensorDampenerScript.load(), NaniteRepairPaste.load(), XLTorpedo.load(), AdvancedRailgunCharge.load(), FestivalCharges.load(), AdvancedRocket.load(), AutoTargetingHeavyMissile.load(), AutoTargetingCruiseMissile.load(), Mine.load(), AutoTargetingLightMissile.load(), HeavyAssaultMissile.load(), AdvancedBlasterCharge.load(), StructureAntiSubcapitalMissile.load(), MiningCrystal.load(), HeavyMissile.load(), CapacitorBoosterCharge.load(), TrackingDisruptionScript.load(), HeavyDefenderMissile.load(), AdvancedXLTorpedo.load(), ObsoleteProbes.load(), FlexShieldHardenerScript.load(), ShieldCommandBurstCharges.load(), CompactXLTorpedo.load(), AdvancedTorpedo.load(), SensorBoosterScript.load(), HybridCharge.load(), ProjectileAmmo.load(), TrackingScript.load(), FlexArmorHardenerScript.load(), AdvancedAutocannonAmmo.load(), FrequencyCrystal.load(), StructureECMScript.load(), DefenderMissiles.load(), ScannerProbe.load(), Modifications.load(), BombECM.load(), BombEnergy.load(), SurveyProbe.load(), InformationCommandBurstCharges.load(), MercoxitMiningCrystal.load(), StructureResistanceSwitcherScript.load(), Torpedo.load(), CruiseMissile.load(), AdvancedHeavyAssaultMissile.load(), OrbitalAssaultUnit.load(), XLCruiseMissile.load(), AdvancedXLCruiseMissile.load(), LightMissile.load(), MiningForemanBurstCharges.load(), PlanetSatellites.load(), WarpDisruptionScript.load(), AdvancedCruiseMissile.load(), SkirmishCommandBurstCharges.load(), Rocket.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
