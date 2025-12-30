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

public class EngineeringComplex
    extends Structure
{
    /**
     * Security status restriction, preventing ships from entering high sec and modules from being activated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinhighsec;
    /**
     * This defines the total capacity of fighters allowed in the fighter bay of the ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightercapacity;
    /**
     * Number of Heavy Fighters the structure can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterstandupheavyslots;
    /**
     * Number of Light Fighters the structure can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterstanduplightslots;
    /**
     * Number of Support Fighters the structure can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterstandupsupportslots;
    /**
     * This defines the total number of fighter launch tubes on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightertubes;
    /**
     * Missile damage attribute used by structures as a workaround for implementing Standup BCS stacking penalties
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int hiddenmissiledamagemultiplier;
    /**
     * Impedance against Remote Repair (shield, armor, hull and energy).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double remoterepairimpedance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigsize;
    /**
     * Cost bonus for Engineering Complexes Structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double strengcostbonus;
    /**
     * Material bonus for Engineering Complexes Structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double strengmatbonus;
    /**
     * Time bonus for Engineering Complexes Structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double strengtimebonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureaoerofrolebonus;
    /**
     * Describes which type of deed fits in this structure
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int structurerequiresdeedtype;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureservicerolebonus;
    /**
     * Distance which tethering will engage / disengage piloted ships.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int tetheringrange;
    /**
     * Attribute on ships used for ship upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradecapacity;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradeslotsleft;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {UpgradeSlotsLeft.INSTANCE, FighterCapacity.INSTANCE, ShieldCapacity.INSTANCE, Hackable.INSTANCE, Uniformity.INSTANCE, ServiceSlots.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, MedSlots.INSTANCE, ArmorKineticDamageResonance.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, PauseShieldRepairDpsThreshold.INSTANCE, PauseArmorRepairDpsThreshold.INSTANCE, PauseHullRepairDpsThreshold.INSTANCE, StructureRequiresDeedType.INSTANCE, MaximumRangeCap.INSTANCE, StructureServiceRoleBonus.INSTANCE, StrEngMatBonus.INSTANCE, FighterTubes.INSTANCE, SignatureRadius.INSTANCE, StrEngCostBonus.INSTANCE, StrEngTimeBonus.INSTANCE, CpuOutput.INSTANCE, FighterStandupLightSlots.INSTANCE, FighterStandupSupportSlots.INSTANCE, DisallowInHighSec.INSTANCE, FighterStandupHeavySlots.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, StructureFullPowerStateHitpointMultiplier.INSTANCE, StructureAoERoFRoleBonus.INSTANCE, HiddenMissileDamageMultiplier.INSTANCE, HiddenArmorHPMultiplier.INSTANCE, MaxLockedTargets.INSTANCE, SensorDampenerResistance.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, RemoteRepairImpedance.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, MaxTargetRange.INSTANCE, ECMResistance.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RemoteAssistanceImpedance.INSTANCE, TetheringRange.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, LauncherSlotsLeft.INSTANCE, CargoDeliveryRange.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, RigSlots.INSTANCE, ShieldDamageLimit.INSTANCE, ArmorDamageLimit.INSTANCE, StructureDamageLimit.INSTANCE, StructurePowerStateArmorPlatingMultiplier.INSTANCE, EnergyWarfareResistance.INSTANCE, TierDifficulty.INSTANCE })));
    public static final EngineeringComplex.MetaGroup METAGROUP = new EngineeringComplex.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1970 :
            {
                return disallowinhighsec;
            }
            case  2055 :
            {
                return fightercapacity;
            }
            case  2739 :
            {
                return fighterstandupheavyslots;
            }
            case  2737 :
            {
                return fighterstanduplightslots;
            }
            case  2738 :
            {
                return fighterstandupsupportslots;
            }
            case  2216 :
            {
                return fightertubes;
            }
            case  2750 :
            {
                return hiddenmissiledamagemultiplier;
            }
            case  2116 :
            {
                return remoterepairimpedance;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  2601 :
            {
                return strengcostbonus;
            }
            case  2600 :
            {
                return strengmatbonus;
            }
            case  2602 :
            {
                return strengtimebonus;
            }
            case  2749 :
            {
                return structureaoerofrolebonus;
            }
            case  3101 :
            {
                return structurerequiresdeedtype;
            }
            case  2339 :
            {
                return structureservicerolebonus;
            }
            case  2268 :
            {
                return tetheringrange;
            }
            case  1132 :
            {
                return upgradecapacity;
            }
            case  1154 :
            {
                return upgradeslotsleft;
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
    public IMetaGroup<EngineeringComplex> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EngineeringComplex>
    {
        public static final String RESOURCE_PATH = "SDE/types/structure/EngineeringComplex.yaml";
        private Map<Integer, EngineeringComplex> cache = (null);

        @Override
        public IMetaCategory<? super EngineeringComplex> category() {
            return Structure.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1404;
        }

        @Override
        public String getName() {
            return "EngineeringComplex";
        }

        @Override
        public synchronized Map<Integer, EngineeringComplex> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(EngineeringComplex.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, EngineeringComplex> types;
        }
    }
}
