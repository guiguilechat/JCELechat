package fr.guiguilechat.jcelechat.model.sde.types.starbase;

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
import fr.guiguilechat.jcelechat.model.sde.types.Starbase;

public class ControlTower
    extends Starbase
{
    /**
     * DO NOT MESS WITH. The amount of damage done to the entities armor hit points. Starting armor damage.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int armordamage;
    /**
     * Secondary cargo space allowed, meant to supplement capacity. This is currently used exclusively for Strontium storage on starbases.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int capacitysecondary;
    /**
     * Bonus attribute to entity Target Switch Delay
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int controltowerewtargetswitchdelaybonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int controltowerhybriddamagebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int controltowerlaserdamagebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int controltowerlaseroptimalbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int controltowermissilerofbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int controltowermissilevelocitybonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int controltowerprojectilefalloffbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int controltowerprojectileoptimalbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int controltowerprojectilerofbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int controltowersilocapacitybonus;
    /**
     * numeric classification for tower size
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int controltowersize;
    /**
     * CPU output of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cpuoutput;
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
    @DefaultRealValue(1.0)
    public double hullemdamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullexplosivedamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullkineticdamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullthermaldamageresonance;
    /**
     * The distance that structures have to be from a control tower in order to work with it.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxstructuredistance;
    /**
     * Maximum range at which the scanner can lock a target.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxtargetrange;
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
     * How many meters from the standard warp-in distance a moon can be anchored from.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int moonanchordistance;
    /**
     * The interval for fuel consumption of a control tower
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int poscontroltowerperiod;
    /**
     * The maximum distance from the control tower, at which structures can be controlled.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(15000)
    public int posstructurecontroldistancemax;
    /**
     * power output of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double poweroutput;
    /**
     * Autogenerated skill attribute, prototypingBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int prototypingbonus;
    /**
     * The distance at which to react when relevant objects come within range.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int proximityrange;
    /**
     * Multiplies EM damage taken by shield
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double shieldemdamageresonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double shieldexplosivedamageresonance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double shieldkineticdamageresonance;
    /**
     * Radius of the force shield that this structure generates.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shieldradius;
    /**
     * Multiplies THERMAL damage taken by Shield. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double shieldthermaldamageresonance;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shielduniformity;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double uniformity;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ControlTowerEwTargetSwitchDelayBonus.INSTANCE, ControlTowerSize.INSTANCE, ShieldCapacity.INSTANCE, Uniformity.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, MaxStructureDistance.INSTANCE, ArmorDamage.INSTANCE, PowerOutput.INSTANCE, ArmorUniformity.INSTANCE, StructureUniformity.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, ControlTowerMissileVelocityBonus.INSTANCE, ProximityRange.INSTANCE, MetaGroupID.INSTANCE, UnanchoringDelay.INSTANCE, OnliningDelay.INSTANCE, ShieldRadius.INSTANCE, SignatureRadius.INSTANCE, AnchoringDelay.INSTANCE, CpuOutput.INSTANCE, PosStructureControlDistanceMax.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, MoonAnchorDistance.INSTANCE, MaxTargetRange.INSTANCE, HullEmDamageResonance.INSTANCE, HullExplosiveDamageResonance.INSTANCE, HullKineticDamageResonance.INSTANCE, ScanRadarStrength.INSTANCE, HullThermalDamageResonance.INSTANCE, CapacitySecondary.INSTANCE, ScanLadarStrength.INSTANCE, PosControlTowerPeriod.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, ControlTowerLaserDamageBonus.INSTANCE, PrototypingBonus.INSTANCE, ShieldRechargeRate.INSTANCE, ShieldUniformity.INSTANCE, DisallowOffensiveModifiers.INSTANCE, ControlTowerLaserOptimalBonus.INSTANCE, ControlTowerProjectileOptimalBonus.INSTANCE, ControlTowerProjectileFallOffBonus.INSTANCE, ControlTowerProjectileROFBonus.INSTANCE, ControlTowerMissileROFBonus.INSTANCE, ControlTowerSiloCapacityBonus.INSTANCE, ControlTowerHybridDamageBonus.INSTANCE })));
    public static final ControlTower.MetaGroup METAGROUP = new ControlTower.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  266 :
            {
                return armordamage;
            }
            case  1233 :
            {
                return capacitysecondary;
            }
            case  770 :
            {
                return controltowerewtargetswitchdelaybonus;
            }
            case  766 :
            {
                return controltowerhybriddamagebonus;
            }
            case  728 :
            {
                return controltowerlaserdamagebonus;
            }
            case  750 :
            {
                return controltowerlaseroptimalbonus;
            }
            case  755 :
            {
                return controltowermissilerofbonus;
            }
            case  792 :
            {
                return controltowermissilevelocitybonus;
            }
            case  753 :
            {
                return controltowerprojectilefalloffbonus;
            }
            case  752 :
            {
                return controltowerprojectileoptimalbonus;
            }
            case  754 :
            {
                return controltowerprojectilerofbonus;
            }
            case  757 :
            {
                return controltowersilocapacitybonus;
            }
            case  1031 :
            {
                return controltowersize;
            }
            case  48 :
            {
                return cpuoutput;
            }
            case  2244 :
            {
                return fighterabilityanticapitalmissileresistance;
            }
            case  974 :
            {
                return hullemdamageresonance;
            }
            case  975 :
            {
                return hullexplosivedamageresonance;
            }
            case  976 :
            {
                return hullkineticdamageresonance;
            }
            case  977 :
            {
                return hullthermaldamageresonance;
            }
            case  650 :
            {
                return maxstructuredistance;
            }
            case  76 :
            {
                return maxtargetrange;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  711 :
            {
                return moonanchordistance;
            }
            case  722 :
            {
                return poscontroltowerperiod;
            }
            case  1214 :
            {
                return posstructurecontroldistancemax;
            }
            case  11 :
            {
                return poweroutput;
            }
            case  473 :
            {
                return prototypingbonus;
            }
            case  154 :
            {
                return proximityrange;
            }
            case  271 :
            {
                return shieldemdamageresonance;
            }
            case  272 :
            {
                return shieldexplosivedamageresonance;
            }
            case  273 :
            {
                return shieldkineticdamageresonance;
            }
            case  680 :
            {
                return shieldradius;
            }
            case  274 :
            {
                return shieldthermaldamageresonance;
            }
            case  484 :
            {
                return shielduniformity;
            }
            case  136 :
            {
                return uniformity;
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
    public IMetaGroup<ControlTower> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ControlTower>
    {
        public static final String RESOURCE_PATH = "SDE/types/starbase/ControlTower.yaml";
        private Map<Integer, ControlTower> cache = (null);

        @Override
        public IMetaCategory<? super ControlTower> category() {
            return Starbase.METACAT;
        }

        @Override
        public int getGroupId() {
            return  365;
        }

        @Override
        public String getName() {
            return "ControlTower";
        }

        @Override
        public synchronized Map<Integer, ControlTower> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ControlTower.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, ControlTower> types;
        }
    }
}
