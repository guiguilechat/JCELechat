package fr.guiguilechat.jcelechat.model.sde.types.structure;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorDamageLimit;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanHaveArmorPhases;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanHaveAutoRepair;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CargoDeliveryRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowInHighSec;
import fr.guiguilechat.jcelechat.model.sde.attributes.ECMResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyWarfareResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiCapitalMissileResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.HiSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.HiddenArmorHPMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaximumRangeCap;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.OutputMoonMaterialBayCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.PauseArmorRepairDpsThreshold;
import fr.guiguilechat.jcelechat.model.sde.attributes.PauseHullRepairDpsThreshold;
import fr.guiguilechat.jcelechat.model.sde.attributes.PauseShieldRepairDpsThreshold;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.PreFitServiceSlot0;
import fr.guiguilechat.jcelechat.model.sde.attributes.PreFitStructureCore;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteAssistanceImpedance;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.SensorDampenerResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ServiceSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldDamageLimit;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebifierResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureDamageLimit;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureFullPowerStateHitpointMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructurePowerStateArmorPlatingMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TierDifficulty;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.WeaponDisruptionResistance;
import fr.guiguilechat.jcelechat.model.sde.types.Structure;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class UpwellMoonDrill
    extends Structure
{
    /**
     * Can a structure have armor phases
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(1)
    public int canhavearmorphases;
    /**
     * Set's whether or not a structure will automatically repair itself.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(1)
    public int canhaveautorepair;
    /**
     * Security status restriction, preventing ships from entering high sec and modules from being activated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinhighsec;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int outputmoonmaterialbaycapacity;
    /**
     * Module type ID to pre-fit into service slot 0
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int prefitserviceslot0;
    /**
     * Module type ID to pre-fit as structure core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int prefitstructurecore;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int scanspeed;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double structureuniformity;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ShieldCapacity.INSTANCE, Hackable.INSTANCE, Uniformity.INSTANCE, ServiceSlots.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, CanHaveAutoRepair.INSTANCE, CanHaveArmorPhases.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, StructureUniformity.INSTANCE, MedSlots.INSTANCE, ArmorKineticDamageResonance.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, Charge.INSTANCE, PauseShieldRepairDpsThreshold.INSTANCE, PauseArmorRepairDpsThreshold.INSTANCE, PauseHullRepairDpsThreshold.INSTANCE, MaximumRangeCap.INSTANCE, Radius.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, DisallowInHighSec.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, StructureFullPowerStateHitpointMultiplier.INSTANCE, OutputMoonMaterialBayCapacity.INSTANCE, HiddenArmorHPMultiplier.INSTANCE, SensorDampenerResistance.INSTANCE, MaxLockedTargets.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, PreFitStructureCore.INSTANCE, MaxTargetRange.INSTANCE, ECMResistance.INSTANCE, ScanSpeed.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RemoteAssistanceImpedance.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, LauncherSlotsLeft.INSTANCE, CargoDeliveryRange.INSTANCE, PreFitServiceSlot0 .INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, ShieldDamageLimit.INSTANCE, ArmorDamageLimit.INSTANCE, StructureDamageLimit.INSTANCE, StructurePowerStateArmorPlatingMultiplier.INSTANCE, EnergyWarfareResistance.INSTANCE, TierDifficulty.INSTANCE })));
    public static final UpwellMoonDrill.MetaGroup METAGROUP = new UpwellMoonDrill.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  5771 :
            {
                return canhavearmorphases;
            }
            case  5770 :
            {
                return canhaveautorepair;
            }
            case  1970 :
            {
                return disallowinhighsec;
            }
            case  5693 :
            {
                return outputmoonmaterialbaycapacity;
            }
            case  2792 :
            {
                return prefitserviceslot0;
            }
            case  5701 :
            {
                return prefitstructurecore;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  525 :
            {
                return structureuniformity;
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
    public IMetaGroup<UpwellMoonDrill> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<UpwellMoonDrill>
    {
        public static final String RESOURCE_PATH = "SDE/types/structure/UpwellMoonDrill.yaml";
        private Map<Integer, UpwellMoonDrill> cache = (null);

        @Override
        public IMetaCategory<? super UpwellMoonDrill> category() {
            return Structure.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4744;
        }

        @Override
        public String getName() {
            return "UpwellMoonDrill";
        }

        @Override
        public synchronized Map<Integer, UpwellMoonDrill> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(UpwellMoonDrill.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, UpwellMoonDrill> types;
        }
    }
}
