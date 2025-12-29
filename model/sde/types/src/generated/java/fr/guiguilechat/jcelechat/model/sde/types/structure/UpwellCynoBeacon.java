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

public class UpwellCynoBeacon
    extends Structure
{
    /**
     * Module type ID to pre-fit into service slot 0
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int prefitserviceslot0;
    /**
     * Impedance against Remote Repair (shield, armor, hull and energy).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double remoterepairimpedance;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ShieldCapacity.INSTANCE, Hackable.INSTANCE, Uniformity.INSTANCE, ServiceSlots.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, StructureUniformity.INSTANCE, MedSlots.INSTANCE, ArmorKineticDamageResonance.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, PauseShieldRepairDpsThreshold.INSTANCE, PauseArmorRepairDpsThreshold.INSTANCE, PauseHullRepairDpsThreshold.INSTANCE, MaximumRangeCap.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, StructureFullPowerStateHitpointMultiplier.INSTANCE, HiddenArmorHPMultiplier.INSTANCE, MaxLockedTargets.INSTANCE, SensorDampenerResistance.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, RemoteRepairImpedance.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, MaxTargetRange.INSTANCE, ECMResistance.INSTANCE, ScanSpeed.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RemoteAssistanceImpedance.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, LauncherSlotsLeft.INSTANCE, CargoDeliveryRange.INSTANCE, PreFitServiceSlot0 .INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, RigSlots.INSTANCE, ShieldDamageLimit.INSTANCE, ArmorDamageLimit.INSTANCE, StructureDamageLimit.INSTANCE, StructurePowerStateArmorPlatingMultiplier.INSTANCE, EnergyWarfareResistance.INSTANCE, TierDifficulty.INSTANCE })));
    public static final UpwellCynoBeacon.MetaGroup METAGROUP = new UpwellCynoBeacon.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2792 :
            {
                return prefitserviceslot0;
            }
            case  2116 :
            {
                return remoterepairimpedance;
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
    public IMetaGroup<UpwellCynoBeacon> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<UpwellCynoBeacon>
    {
        public static final String RESOURCE_PATH = "SDE/types/structure/UpwellCynoBeacon.yaml";
        private Map<Integer, UpwellCynoBeacon> cache = (null);

        @Override
        public IMetaCategory<? super UpwellCynoBeacon> category() {
            return Structure.METACAT;
        }

        @Override
        public int getGroupId() {
            return  2017;
        }

        @Override
        public String getName() {
            return "UpwellCynoBeacon";
        }

        @Override
        public synchronized Map<Integer, UpwellCynoBeacon> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(UpwellCynoBeacon.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, UpwellCynoBeacon> types;
        }
    }
}
