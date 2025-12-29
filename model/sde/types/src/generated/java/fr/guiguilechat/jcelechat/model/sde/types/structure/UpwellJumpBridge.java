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

public class UpwellJumpBridge
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int gatemaxjumpmass;
    /**
     * Type that is used for consumption from cargo hold when activating jump drive operation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int jumpdriveconsumptiontype;
    /**
     * Range in light years the ship can maximum jump to.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double jumpdriverange;
    /**
     * Additional units of fuel that are consumed on each jump through a jump portal, not subject to any of the mass or distance multipliers
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int jumpportaladditionalconsumption;
    /**
     * Multiplier used to calculate amount of quantity used for jumping via portals based on mass of ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double jumpportalconsumptionmassfactor;
    /**
     * Module type ID to pre-fit into service slot 0
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int prefitserviceslot0;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ShieldCapacity.INSTANCE, Hackable.INSTANCE, Uniformity.INSTANCE, ServiceSlots.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, CanHaveAutoRepair.INSTANCE, CanHaveArmorPhases.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, StructureUniformity.INSTANCE, MedSlots.INSTANCE, ArmorKineticDamageResonance.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, PauseShieldRepairDpsThreshold.INSTANCE, PauseArmorRepairDpsThreshold.INSTANCE, PauseHullRepairDpsThreshold.INSTANCE, MaximumRangeCap.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, StructureFullPowerStateHitpointMultiplier.INSTANCE, HiddenArmorHPMultiplier.INSTANCE, MaxLockedTargets.INSTANCE, SensorDampenerResistance.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, MaxTargetRange.INSTANCE, ECMResistance.INSTANCE, ScanSpeed.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RemoteAssistanceImpedance.INSTANCE, ShieldRechargeRate.INSTANCE, JumpDriveConsumptionType.INSTANCE, CapacitorCapacity.INSTANCE, JumpDriveRange.INSTANCE, ShieldUniformity.INSTANCE, LauncherSlotsLeft.INSTANCE, CargoDeliveryRange.INSTANCE, PreFitServiceSlot0 .INSTANCE, JumpPortalConsumptionMassFactor.INSTANCE, JumpPortalAdditionalConsumption.INSTANCE, KineticDamageResonance.INSTANCE, GateMaxJumpMass.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, RigSlots.INSTANCE, ShieldDamageLimit.INSTANCE, ArmorDamageLimit.INSTANCE, StructureDamageLimit.INSTANCE, StructurePowerStateArmorPlatingMultiplier.INSTANCE, EnergyWarfareResistance.INSTANCE, TierDifficulty.INSTANCE })));
    public static final UpwellJumpBridge.MetaGroup METAGROUP = new UpwellJumpBridge.MetaGroup();

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
            case  2798 :
            {
                return gatemaxjumpmass;
            }
            case  866 :
            {
                return jumpdriveconsumptiontype;
            }
            case  867 :
            {
                return jumpdriverange;
            }
            case  2793 :
            {
                return jumpportaladditionalconsumption;
            }
            case  1001 :
            {
                return jumpportalconsumptionmassfactor;
            }
            case  2792 :
            {
                return prefitserviceslot0;
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
    public IMetaGroup<UpwellJumpBridge> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<UpwellJumpBridge>
    {
        public static final String RESOURCE_PATH = "SDE/types/structure/UpwellJumpBridge.yaml";
        private Map<Integer, UpwellJumpBridge> cache = (null);

        @Override
        public IMetaCategory<? super UpwellJumpBridge> category() {
            return Structure.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1408;
        }

        @Override
        public String getName() {
            return "UpwellJumpBridge";
        }

        @Override
        public synchronized Map<Integer, UpwellJumpBridge> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(UpwellJumpBridge.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, UpwellJumpBridge> types;
        }
    }
}
