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

public class Refinery
    extends Structure
{
    /**
     * Delay for exploding moon mining chunk into asteroid field
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10800)
    public int autofracturedelay;
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
     * This attribute doesn't directly impact the asteroid decay, but is used to expose the decay time to the show-info window
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(48)
    public int moonasteroiddecaydisplayvalue;
    /**
     * Decay time for asteroid created from moon spew
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int moonasteroiddecaytimemultiplier;
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
     * Time bonus for Refinery Structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double strreactiontimemultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double strrefiningyieldbonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureaoerofrolebonus;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double structuregasdecompressionefficiencybonus;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {UpgradeSlotsLeft.INSTANCE, FighterCapacity.INSTANCE, ShieldCapacity.INSTANCE, Hackable.INSTANCE, Uniformity.INSTANCE, ServiceSlots.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, AutoFractureDelay.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, MedSlots.INSTANCE, ArmorKineticDamageResonance.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, MoonAsteroidDecayTimeMultiplier.INSTANCE, ShieldThermalDamageResonance.INSTANCE, PauseShieldRepairDpsThreshold.INSTANCE, PauseArmorRepairDpsThreshold.INSTANCE, PauseHullRepairDpsThreshold.INSTANCE, StructureRequiresDeedType.INSTANCE, MaximumRangeCap.INSTANCE, StrReactionTimeMultiplier.INSTANCE, StrRefiningYieldBonus.INSTANCE, StructureServiceRoleBonus.INSTANCE, MoonAsteroidDecayDisplayValue.INSTANCE, FighterTubes.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, FighterStandupLightSlots.INSTANCE, FighterStandupSupportSlots.INSTANCE, FighterStandupHeavySlots.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, StructureFullPowerStateHitpointMultiplier.INSTANCE, StructureGasDecompressionEfficiencyBonus.INSTANCE, StructureAoERoFRoleBonus.INSTANCE, HiddenMissileDamageMultiplier.INSTANCE, HiddenArmorHPMultiplier.INSTANCE, MaxLockedTargets.INSTANCE, SensorDampenerResistance.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, RemoteRepairImpedance.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, MaxTargetRange.INSTANCE, ECMResistance.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RemoteAssistanceImpedance.INSTANCE, TetheringRange.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, LauncherSlotsLeft.INSTANCE, CargoDeliveryRange.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, RigSlots.INSTANCE, ShieldDamageLimit.INSTANCE, ArmorDamageLimit.INSTANCE, StructureDamageLimit.INSTANCE, StructurePowerStateArmorPlatingMultiplier.INSTANCE, EnergyWarfareResistance.INSTANCE, TierDifficulty.INSTANCE })));
    public static final Refinery.MetaGroup METAGROUP = new Refinery.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2698 :
            {
                return autofracturedelay;
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
            case  2728 :
            {
                return moonasteroiddecaydisplayvalue;
            }
            case  2706 :
            {
                return moonasteroiddecaytimemultiplier;
            }
            case  2116 :
            {
                return remoterepairimpedance;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  2721 :
            {
                return strreactiontimemultiplier;
            }
            case  2722 :
            {
                return strrefiningyieldbonus;
            }
            case  2749 :
            {
                return structureaoerofrolebonus;
            }
            case  3261 :
            {
                return structuregasdecompressionefficiencybonus;
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
    public IMetaGroup<Refinery> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Refinery>
    {
        public static final String RESOURCE_PATH = "SDE/types/structure/Refinery.yaml";
        private Map<Integer, Refinery> cache = (null);

        @Override
        public IMetaCategory<? super Refinery> category() {
            return Structure.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1406;
        }

        @Override
        public String getName() {
            return "Refinery";
        }

        @Override
        public synchronized Map<Integer, Refinery> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Refinery.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Refinery> types;
        }
    }
}
