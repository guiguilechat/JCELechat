package fr.guiguilechat.jcelechat.model.sde.types.starbase;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.Starbase;
import org.yaml.snakeyaml.Yaml;

public class ControlTower
    extends Starbase
{
    /**
     * DO NOT MESS WITH. The amount of damage done to the entities armor hit points. Starting armor damage.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorDamage;
    /**
     * Secondary cargo space allowed, meant to supplement capacity. This is currently used exclusively for Strontium storage on starbases.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int CapacitySecondary;
    /**
     * Bonus attribute to entity Target Switch Delay
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerEwTargetSwitchDelayBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerHybridDamageBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerLaserDamageBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerLaserOptimalBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerMissileROFBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerMissileVelocityBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerProjectileFallOffBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerProjectileOptimalBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerProjectileROFBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerSiloCapacityBonus;
    /**
     * numeric classification for tower size
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int ControlTowerSize;
    /**
     * CPU output of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CpuOutput;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiCapitalMissileResistance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HullEmDamageResonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HullExplosiveDamageResonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HullKineticDamageResonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HullThermalDamageResonance;
    /**
     * The distance that structures have to be from a control tower in order to work with it.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxStructureDistance;
    /**
     * Maximum range at which the scanner can lock a target.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxTargetRange;
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
    public int MetaGroupID;
    /**
     * How many meters from the standard warp-in distance a moon can be anchored from.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MoonAnchorDistance;
    /**
     * The interval for fuel consumption of a control tower
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int PosControlTowerPeriod;
    /**
     * The maximum distance from the control tower, at which structures can be controlled.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(15000)
    public int PosStructureControlDistanceMax;
    /**
     * power output of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerOutput;
    /**
     * Autogenerated skill attribute, prototypingBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PrototypingBonus;
    /**
     * The distance at which to react when relevant objects come within range.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ProximityRange;
    /**
     * DO NOT MESS WITH. Helper attribute for entities, stands in for the shield charge.
     * The amount of starting shield capacity of the NPC.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldCharge;
    /**
     * Multiplies EM damage taken by shield
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ShieldEmDamageResonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ShieldExplosiveDamageResonance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ShieldKineticDamageResonance;
    /**
     * Radius of the force shield that this structure generates.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldRadius;
    /**
     * Multiplies THERMAL damage taken by Shield. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ShieldThermalDamageResonance;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldUniformity;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Uniformity;
    public static final ControlTower.MetaGroup METAGROUP = new ControlTower.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  266 :
            {
                return ArmorDamage;
            }
            case  1233 :
            {
                return CapacitySecondary;
            }
            case  770 :
            {
                return ControlTowerEwTargetSwitchDelayBonus;
            }
            case  766 :
            {
                return ControlTowerHybridDamageBonus;
            }
            case  728 :
            {
                return ControlTowerLaserDamageBonus;
            }
            case  750 :
            {
                return ControlTowerLaserOptimalBonus;
            }
            case  755 :
            {
                return ControlTowerMissileROFBonus;
            }
            case  792 :
            {
                return ControlTowerMissileVelocityBonus;
            }
            case  753 :
            {
                return ControlTowerProjectileFallOffBonus;
            }
            case  752 :
            {
                return ControlTowerProjectileOptimalBonus;
            }
            case  754 :
            {
                return ControlTowerProjectileROFBonus;
            }
            case  757 :
            {
                return ControlTowerSiloCapacityBonus;
            }
            case  1031 :
            {
                return ControlTowerSize;
            }
            case  48 :
            {
                return CpuOutput;
            }
            case  2244 :
            {
                return FighterAbilityAntiCapitalMissileResistance;
            }
            case  974 :
            {
                return HullEmDamageResonance;
            }
            case  975 :
            {
                return HullExplosiveDamageResonance;
            }
            case  976 :
            {
                return HullKineticDamageResonance;
            }
            case  977 :
            {
                return HullThermalDamageResonance;
            }
            case  650 :
            {
                return MaxStructureDistance;
            }
            case  76 :
            {
                return MaxTargetRange;
            }
            case  1692 :
            {
                return MetaGroupID;
            }
            case  711 :
            {
                return MoonAnchorDistance;
            }
            case  722 :
            {
                return PosControlTowerPeriod;
            }
            case  1214 :
            {
                return PosStructureControlDistanceMax;
            }
            case  11 :
            {
                return PowerOutput;
            }
            case  473 :
            {
                return PrototypingBonus;
            }
            case  154 :
            {
                return ProximityRange;
            }
            case  264 :
            {
                return ShieldCharge;
            }
            case  271 :
            {
                return ShieldEmDamageResonance;
            }
            case  272 :
            {
                return ShieldExplosiveDamageResonance;
            }
            case  273 :
            {
                return ShieldKineticDamageResonance;
            }
            case  680 :
            {
                return ShieldRadius;
            }
            case  274 :
            {
                return ShieldThermalDamageResonance;
            }
            case  484 :
            {
                return ShieldUniformity;
            }
            case  136 :
            {
                return Uniformity;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<ControlTower> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ControlTower>
    {
        public static final String RESOURCE_PATH = "SDE/items/starbase/ControlTower.yaml";
        private Map<String, ControlTower> cache = (null);

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
        public synchronized Map<String, ControlTower> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(ControlTower.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ControlTower> items;
        }
    }
}
