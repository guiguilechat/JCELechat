package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedArtilleryAmmo;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedAutocannonAmmo;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedBeamLaserCrystal;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedBlasterCharge;
import fr.guiguilechat.jcelechat.model.sde.types.charge.AdvancedCondenserPack;
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
import fr.guiguilechat.jcelechat.model.sde.types.charge.CondenserPack;
import fr.guiguilechat.jcelechat.model.sde.types.charge.CruiseMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.DefenderMissiles;
import fr.guiguilechat.jcelechat.model.sde.types.charge.ExoticPlasmaCharge;
import fr.guiguilechat.jcelechat.model.sde.types.charge.FestivalCharges;
import fr.guiguilechat.jcelechat.model.sde.types.charge.FlexArmorHardenerScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.FlexShieldHardenerScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.FrequencyCrystal;
import fr.guiguilechat.jcelechat.model.sde.types.charge.GuidanceDisruptionScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.GuidedBomb;
import fr.guiguilechat.jcelechat.model.sde.types.charge.HeavyAssaultMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.HeavyMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.HybridCharge;
import fr.guiguilechat.jcelechat.model.sde.types.charge.InformationCommandBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.types.charge.InterdictionBurstProbes;
import fr.guiguilechat.jcelechat.model.sde.types.charge.InterdictionProbe;
import fr.guiguilechat.jcelechat.model.sde.types.charge.LightMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.MercoxitMiningCrystal;
import fr.guiguilechat.jcelechat.model.sde.types.charge.MiningCrystal;
import fr.guiguilechat.jcelechat.model.sde.types.charge.MiningForemanBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.types.charge.MissileGuidanceScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.NaniteRepairPaste;
import fr.guiguilechat.jcelechat.model.sde.types.charge.ProjectileAmmo;
import fr.guiguilechat.jcelechat.model.sde.types.charge.Rocket;
import fr.guiguilechat.jcelechat.model.sde.types.charge.SCARABBreacherPods;
import fr.guiguilechat.jcelechat.model.sde.types.charge.ScannerProbe;
import fr.guiguilechat.jcelechat.model.sde.types.charge.SensorBoosterScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.SensorDampenerScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.ShieldCommandBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.types.charge.SkirmishCommandBurstCharges;
import fr.guiguilechat.jcelechat.model.sde.types.charge.StructureAntiCapitalMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.StructureAntiSubcapitalMissile;
import fr.guiguilechat.jcelechat.model.sde.types.charge.StructureAreaDenialAmmunition;
import fr.guiguilechat.jcelechat.model.sde.types.charge.StructureECMScript;
import fr.guiguilechat.jcelechat.model.sde.types.charge.StructureFestivalCharges;
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
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Capacity.INSTANCE })));
    public static final Charge.MetaCat METACAT = new Charge.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  38 :
            {
                return capacity;
            }
            case  162 :
            {
                return radius;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

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
            return Arrays.asList(ProjectileAmmo.METAGROUP, HybridCharge.METAGROUP, FrequencyCrystal.METAGROUP, CapacitorBoosterCharge.METAGROUP, DefenderMissiles.METAGROUP, Torpedo.METAGROUP, StructureAreaDenialAmmunition.METAGROUP, Bomb.METAGROUP, AdvancedAutocannonAmmo.METAGROUP, AdvancedRailgunCharge.METAGROUP, AdvancedBeamLaserCrystal.METAGROUP, AdvancedPulseLaserCrystal.METAGROUP, AdvancedArtilleryAmmo.METAGROUP, AdvancedBlasterCharge.METAGROUP, LightMissile.METAGROUP, HeavyMissile.METAGROUP, CruiseMissile.METAGROUP, Rocket.METAGROUP, AutoTargetingLightMissile.METAGROUP, AutoTargetingHeavyMissile.METAGROUP, AutoTargetingCruiseMissile.METAGROUP, XLTorpedo.METAGROUP, ScannerProbe.METAGROUP, MiningCrystal.METAGROUP, SurveyProbe.METAGROUP, FestivalCharges.METAGROUP, InterdictionProbe.METAGROUP, AdvancedRocket.METAGROUP, AdvancedLightMissile.METAGROUP, AdvancedHeavyAssaultMissile.METAGROUP, AdvancedHeavyMissile.METAGROUP, AdvancedCruiseMissile.METAGROUP, AdvancedTorpedo.METAGROUP, MercoxitMiningCrystal.METAGROUP, SCARABBreacherPods.METAGROUP, HeavyAssaultMissile.METAGROUP, BombECM.METAGROUP, BombEnergy.METAGROUP, TrackingScript.METAGROUP, WarpDisruptionScript.METAGROUP, TrackingDisruptionScript.METAGROUP, SensorBoosterScript.METAGROUP, SensorDampenerScript.METAGROUP, NaniteRepairPaste.METAGROUP, XLCruiseMissile.METAGROUP, MissileGuidanceScript.METAGROUP, StructureAntiCapitalMissile.METAGROUP, StructureAntiSubcapitalMissile.METAGROUP, GuidedBomb.METAGROUP, StructureECMScript.METAGROUP, StructureWarpDisruptorScript.METAGROUP, GuidanceDisruptionScript.METAGROUP, AdvancedXLTorpedo.METAGROUP, AdvancedXLCruiseMissile.METAGROUP, FlexArmorHardenerScript.METAGROUP, FlexShieldHardenerScript.METAGROUP, ShieldCommandBurstCharges.METAGROUP, MiningForemanBurstCharges.METAGROUP, SkirmishCommandBurstCharges.METAGROUP, InformationCommandBurstCharges.METAGROUP, ArmorCommandBurstCharges.METAGROUP, StructureFestivalCharges.METAGROUP, ExoticPlasmaCharge.METAGROUP, AdvancedExoticPlasmaCharge.METAGROUP, AdvancedCondenserPack.METAGROUP, CondenserPack.METAGROUP, InterdictionBurstProbes.METAGROUP);
        }
    }
}
