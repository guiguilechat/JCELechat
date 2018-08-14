package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
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
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.CruiseMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.DefenderMissiles;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.ExoticPlasmaCharge;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.FestivalCharges;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.FlexArmorHardenerScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.FlexShieldHardenerScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.FrequencyCrystal;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.GuidanceDisruptionScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.HeavyAssaultMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.HeavyMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.HybridCharge;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.InformationCommandBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.InterdictionProbe;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.LightMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.MercoxitMiningCrystal;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.MiningCrystal;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.MiningForemanBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.MissileGuidanceScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.NaniteRepairPaste;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.ProjectileAmmo;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.Rocket;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.ScannerProbe;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.SensorBoosterScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.SensorDampenerScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.ShieldCommandBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.SkirmishCommandBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.StructureAntiCapitalMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.StructureAntiSubcapitalMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.StructureECMScript;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.StructureFestivalCharges;
import fr.guiguilechat.jcelechat.model.sde.items.types.charge.StructureGuidedBomb;
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
    public final static Charge.MetaCat METACAT = new Charge.MetaCat();

    @Override
    public IMetaCategory<Charge> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Charge>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends Charge> [] groups = new IMetaGroup[] {ProjectileAmmo.METAGROUP, HybridCharge.METAGROUP, FrequencyCrystal.METAGROUP, CapacitorBoosterCharge.METAGROUP, DefenderMissiles.METAGROUP, Torpedo.METAGROUP, Bomb.METAGROUP, AdvancedAutocannonAmmo.METAGROUP, AdvancedRailgunCharge.METAGROUP, AdvancedBeamLaserCrystal.METAGROUP, AdvancedPulseLaserCrystal.METAGROUP, AdvancedArtilleryAmmo.METAGROUP, AdvancedBlasterCharge.METAGROUP, LightMissile.METAGROUP, HeavyMissile.METAGROUP, CruiseMissile.METAGROUP, Rocket.METAGROUP, AutoTargetingLightMissile.METAGROUP, AutoTargetingHeavyMissile.METAGROUP, AutoTargetingCruiseMissile.METAGROUP, XLTorpedo.METAGROUP, ScannerProbe.METAGROUP, MiningCrystal.METAGROUP, SurveyProbe.METAGROUP, FestivalCharges.METAGROUP, InterdictionProbe.METAGROUP, AdvancedRocket.METAGROUP, AdvancedLightMissile.METAGROUP, AdvancedHeavyAssaultMissile.METAGROUP, AdvancedHeavyMissile.METAGROUP, AdvancedCruiseMissile.METAGROUP, AdvancedTorpedo.METAGROUP, MercoxitMiningCrystal.METAGROUP, HeavyAssaultMissile.METAGROUP, BombECM.METAGROUP, BombEnergy.METAGROUP, TrackingScript.METAGROUP, WarpDisruptionScript.METAGROUP, TrackingDisruptionScript.METAGROUP, SensorBoosterScript.METAGROUP, SensorDampenerScript.METAGROUP, NaniteRepairPaste.METAGROUP, XLCruiseMissile.METAGROUP, MissileGuidanceScript.METAGROUP, StructureAntiCapitalMissile.METAGROUP, StructureAntiSubcapitalMissile.METAGROUP, StructureGuidedBomb.METAGROUP, StructureECMScript.METAGROUP, StructureWarpDisruptorScript.METAGROUP, GuidanceDisruptionScript.METAGROUP, AdvancedXLTorpedo.METAGROUP, AdvancedXLCruiseMissile.METAGROUP, FlexArmorHardenerScript.METAGROUP, FlexShieldHardenerScript.METAGROUP, ShieldCommandBurstCharges.METAGROUP, MiningForemanBurstCharges.METAGROUP, SkirmishCommandBurstCharges.METAGROUP, InformationCommandBurstCharges.METAGROUP, ArmorCommandBurstCharges.METAGROUP, StructureFestivalCharges.METAGROUP, ExoticPlasmaCharge.METAGROUP, AdvancedExoticPlasmaCharge.METAGROUP };

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
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, Charge> load() {
            HashMap<String, Charge> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
