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
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CargoDeliveryRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.ECMResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyWarfareResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiCapitalMissileResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityKamikazeResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterStandupHeavySlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterStandupLightSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterStandupSupportSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterTubes;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.HiSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.HiddenArmorHPMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.HiddenMissileDamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaximumRangeCap;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteAssistanceImpedance;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteRepairImpedance;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSize;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.StrEngCostBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.StrEngTimeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.StrRefiningYieldBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureAoERoFRoleBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureDamageLimit;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureFullPowerStateHitpointMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructurePowerStateArmorPlatingMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureRoleBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureServiceRoleBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TargetPainterResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TetheringRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TierDifficulty;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.WeaponDisruptionResistance;
import fr.guiguilechat.jcelechat.model.sde.types.Structure;
import org.yaml.snakeyaml.Yaml;

public class Citadel
    extends Structure
{
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilityanticapitalmissileresistance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitykamikazeresistance;
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
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metagroupid;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigsize;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int scanspeed;
    /**
     * Cost bonus for Engineering Complexes Structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double strengcostbonus;
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
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int strrefiningyieldbonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureaoerofrolebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structurerolebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureservicerolebonus;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double structureuniformity;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {FighterAbilityKamikazeResistance.INSTANCE, UpgradeSlotsLeft.INSTANCE, Mass.INSTANCE, FighterCapacity.INSTANCE, ShieldCapacity.INSTANCE, Hackable.INSTANCE, Uniformity.INSTANCE, ServiceSlots.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, StructureUniformity.INSTANCE, MedSlots.INSTANCE, ArmorKineticDamageResonance.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, Charge.INSTANCE, MetaGroupID.INSTANCE, StructureRoleBonus.INSTANCE, MaximumRangeCap.INSTANCE, StrRefiningYieldBonus.INSTANCE, Radius.INSTANCE, StructureServiceRoleBonus.INSTANCE, Capacity.INSTANCE, FighterTubes.INSTANCE, SignatureRadius.INSTANCE, StrEngCostBonus.INSTANCE, StrEngTimeBonus.INSTANCE, CpuOutput.INSTANCE, FighterStandupLightSlots.INSTANCE, FighterStandupSupportSlots.INSTANCE, FighterStandupHeavySlots.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, StructureFullPowerStateHitpointMultiplier.INSTANCE, StructureAoERoFRoleBonus.INSTANCE, HiddenMissileDamageMultiplier.INSTANCE, HiddenArmorHPMultiplier.INSTANCE, SensorDampenerResistance.INSTANCE, MaxLockedTargets.INSTANCE, WeaponDisruptionResistance.INSTANCE, TargetPainterResistance.INSTANCE, StasisWebifierResistance.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, RemoteRepairImpedance.INSTANCE, MaxTargetRange.INSTANCE, ECMResistance.INSTANCE, ScanSpeed.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RemoteAssistanceImpedance.INSTANCE, TetheringRange.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, LauncherSlotsLeft.INSTANCE, CargoDeliveryRange.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, ShieldDamageLimit.INSTANCE, ArmorDamageLimit.INSTANCE, StructureDamageLimit.INSTANCE, StructurePowerStateArmorPlatingMultiplier.INSTANCE, MetaLevelOld.INSTANCE, EnergyWarfareResistance.INSTANCE, TierDifficulty.INSTANCE })));
    public static final Citadel.MetaGroup METAGROUP = new Citadel.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2244 :
            {
                return fighterabilityanticapitalmissileresistance;
            }
            case  2433 :
            {
                return fighterabilitykamikazeresistance;
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
            case  1692 :
            {
                return metagroupid;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  2601 :
            {
                return strengcostbonus;
            }
            case  2602 :
            {
                return strengtimebonus;
            }
            case  2722 :
            {
                return strrefiningyieldbonus;
            }
            case  2749 :
            {
                return structureaoerofrolebonus;
            }
            case  2333 :
            {
                return structurerolebonus;
            }
            case  2339 :
            {
                return structureservicerolebonus;
            }
            case  525 :
            {
                return structureuniformity;
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
    public IMetaGroup<Citadel> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Citadel>
    {
        public static final String RESOURCE_PATH = "SDE/types/structure/Citadel.yaml";
        private Map<String, Citadel> cache = (null);

        @Override
        public IMetaCategory<? super Citadel> category() {
            return Structure.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1657;
        }

        @Override
        public String getName() {
            return "Citadel";
        }

        @Override
        public synchronized Map<String, Citadel> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Citadel.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Citadel> types;
        }
    }
}
