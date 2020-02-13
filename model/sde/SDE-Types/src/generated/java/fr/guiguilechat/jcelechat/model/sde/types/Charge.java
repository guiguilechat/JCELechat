package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedArtilleryAmmo;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedAutocannonAmmo;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedBeamLaserCrystal;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedBlasterCharge;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedCruiseMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedExoticPlasmaCharge;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedHeavyAssaultMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedHeavyMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedLightMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedPulseLaserCrystal;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedRailgunCharge;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedRocket;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedTorpedo;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedXLCruiseMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedXLTorpedo;
import fr.guiguilechat.jcelechat.model.sde.types.charge.ArmorCommandBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AutoTargetingCruiseMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AutoTargetingHeavyMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AutoTargetingLightMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.Bomb;
import fr.guiguilechat.jcelechat.model.sde.types.charge.BombECM;
import fr.guiguilechat.jcelechat.model.sde.types.charge.BombEnergy;
import fr.guiguilechat.jcelechat.model.sde.types.charge.CapacitorBoosterCharge;
import fr.guiguilechat.jcelechat.model.sde.types.charge.CruiseMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.DefenderMissiles;
import fr.guiguilechat.jcelechat.model.sde.types.charge.ExoticPlasmaCharge;
import fr.guiguilechat.jcelechat.model.sde.types.charge.FestivalCharges;
import fr.guiguilechat.jcelechat.model.sde.types.charge.FlexArmorHardenerScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.FlexShieldHardenerScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.FrequencyCrystal;
import fr.guiguilechat.jcelechat.model.sde.types.charge.GuidanceDisruptionScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.HeavyAssaultMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.HeavyMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.HybridCharge;
import fr.guiguilechat.jcelechat.model.sde.types.charge.InformationCommandBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.types.charge.InterdictionProbe;
import fr.guiguilechat.jcelechat.model.sde.types.charge.LightMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.MercoxitMiningCrystal;
import fr.guiguilechat.jcelechat.model.sde.types.charge.MiningCrystal;
import fr.guiguilechat.jcelechat.model.sde.types.charge.MiningForemanBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.types.charge.MissileGuidanceScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.NaniteRepairPaste;
import fr.guiguilechat.jcelechat.model.sde.types.charge.ProjectileAmmo;
import fr.guiguilechat.jcelechat.model.sde.types.charge.Rocket;
import fr.guiguilechat.jcelechat.model.sde.types.charge.ScannerProbe;
import fr.guiguilechat.jcelechat.model.sde.types.charge.SensorBoosterScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.SensorDampenerScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.ShieldCommandBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.types.charge.SkirmishCommandBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.types.charge.StructureAntiCapitalMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.StructureAntiSubcapitalMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.StructureECMScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.StructureFestivalCharges;
import fr.guiguilechat.jcelechat.model.sde.types.charge.StructureGuidedBomb;
import fr.guiguilechat.jcelechat.model.sde.types.charge.StructureWarpDisruptorScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.SurveyProbe;
import fr.guiguilechat.jcelechat.model.sde.types.charge.Torpedo;
import fr.guiguilechat.jcelechat.model.sde.types.charge.TrackingDisruptionScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.TrackingScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.WarpDisruptionScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.XLCruiseMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.XLTorpedo;

public abstract class Charge
    extends EveType
{
    public static final Charge.MetaCat METACAT = new Charge.MetaCat();

    @Override
    public IMetaCategory<Charge> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Charge>
    {

        @Override
        public int getCategoryId() {
            return  8;
        }

        @Override
        public String getName() {
            return "Charge";
        }

        @Override
        public Collection<IMetaGroup<? extends Charge>> groups() {
            return Arrays.asList(ProjectileAmmo.METAGROUP, HybridCharge.METAGROUP, FrequencyCrystal.METAGROUP, CapacitorBoosterCharge.METAGROUP, DefenderMissiles.METAGROUP, Torpedo.METAGROUP, Bomb.METAGROUP, AdvancedAutocannonAmmo.METAGROUP, AdvancedRailgunCharge.METAGROUP, AdvancedBeamLaserCrystal.METAGROUP, AdvancedPulseLaserCrystal.METAGROUP, AdvancedArtilleryAmmo.METAGROUP, AdvancedBlasterCharge.METAGROUP, LightMissile.METAGROUP, HeavyMissile.METAGROUP, CruiseMissile.METAGROUP, Rocket.METAGROUP, AutoTargetingLightMissile.METAGROUP, AutoTargetingHeavyMissile.METAGROUP, AutoTargetingCruiseMissile.METAGROUP, XLTorpedo.METAGROUP, ScannerProbe.METAGROUP, MiningCrystal.METAGROUP, SurveyProbe.METAGROUP, FestivalCharges.METAGROUP, InterdictionProbe.METAGROUP, AdvancedRocket.METAGROUP, AdvancedLightMissile.METAGROUP, AdvancedHeavyAssaultMissile.METAGROUP, AdvancedHeavyMissile.METAGROUP, AdvancedCruiseMissile.METAGROUP, AdvancedTorpedo.METAGROUP, MercoxitMiningCrystal.METAGROUP, HeavyAssaultMissile.METAGROUP, BombECM.METAGROUP, BombEnergy.METAGROUP, TrackingScript.METAGROUP, WarpDisruptionScript.METAGROUP, TrackingDisruptionScript.METAGROUP, SensorBoosterScript.METAGROUP, SensorDampenerScript.METAGROUP, NaniteRepairPaste.METAGROUP, XLCruiseMissile.METAGROUP, MissileGuidanceScript.METAGROUP, StructureAntiCapitalMissile.METAGROUP, StructureAntiSubcapitalMissile.METAGROUP, StructureGuidedBomb.METAGROUP, StructureECMScript.METAGROUP, StructureWarpDisruptorScript.METAGROUP, GuidanceDisruptionScript.METAGROUP, AdvancedXLTorpedo.METAGROUP, AdvancedXLCruiseMissile.METAGROUP, FlexArmorHardenerScript.METAGROUP, FlexShieldHardenerScript.METAGROUP, ShieldCommandBurstCharges.METAGROUP, MiningForemanBurstCharges.METAGROUP, SkirmishCommandBurstCharges.METAGROUP, InformationCommandBurstCharges.METAGROUP, ArmorCommandBurstCharges.METAGROUP, StructureFestivalCharges.METAGROUP, ExoticPlasmaCharge.METAGROUP, AdvancedExoticPlasmaCharge.METAGROUP);
        }
    }
}
