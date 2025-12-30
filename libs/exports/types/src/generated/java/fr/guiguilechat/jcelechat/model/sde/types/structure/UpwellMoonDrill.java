package fr.guiguilechat.jcelechat.model.sde.types.structure;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.Structure;

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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ShieldCapacity.INSTANCE, Hackable.INSTANCE, Uniformity.INSTANCE, ServiceSlots.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, CanHaveAutoRepair.INSTANCE, CanHaveArmorPhases.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, StructureUniformity.INSTANCE, MedSlots.INSTANCE, ArmorKineticDamageResonance.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, PauseShieldRepairDpsThreshold.INSTANCE, PauseArmorRepairDpsThreshold.INSTANCE, PauseHullRepairDpsThreshold.INSTANCE, MaximumRangeCap.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, DisallowInHighSec.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, StructureFullPowerStateHitpointMultiplier.INSTANCE, OutputMoonMaterialBayCapacity.INSTANCE, HiddenArmorHPMultiplier.INSTANCE, MaxLockedTargets.INSTANCE, SensorDampenerResistance.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, PreFitStructureCore.INSTANCE, MaxTargetRange.INSTANCE, ECMResistance.INSTANCE, ScanSpeed.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RemoteAssistanceImpedance.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, LauncherSlotsLeft.INSTANCE, CargoDeliveryRange.INSTANCE, PreFitServiceSlot0 .INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, RigSlots.INSTANCE, ShieldDamageLimit.INSTANCE, ArmorDamageLimit.INSTANCE, StructureDamageLimit.INSTANCE, StructurePowerStateArmorPlatingMultiplier.INSTANCE, EnergyWarfareResistance.INSTANCE, TierDifficulty.INSTANCE })));
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
