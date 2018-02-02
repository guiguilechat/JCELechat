package fr.guiguilechat.eveonline.model.sde.items.types.starbase;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Starbase;
import org.yaml.snakeyaml.Yaml;

public class ControlTower
    extends Starbase
{
    /**
     * Bonus attribute to entity Target Switch Delay
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerEwTargetSwitchDelayBonus;
    /**
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldCapacity;
    /**
     * numeric classification for tower size
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int ControlTowerSize;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Uniformity;
    /**
     * DO NOT MESS WITH. Helper attribute for entities, stands in for the shield charge.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldCharge;
    /**
     * The number of hit points on the entities armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorHP;
    /**
     * The distance that structures have to be from a control tower in order to work with it.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxStructureDistance;
    /**
     * DO NOT MESS WITH. The amount of damage done to the entities armor hit points.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorDamage;
    /**
     * power output of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerOutput;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ArmorUniformity;
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
     * Multiplies THERMAL damage taken by Shield. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ShieldThermalDamageResonance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerMissileVelocityBonus;
    /**
     * The distance at which to react when relevant objects come within range.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ProximityRange;
    /**
     * meta group of type
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
    /**
     * Radius of the force shield that this structure generates.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldRadius;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    /**
     * CPU output of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CpuOutput;
    /**
     * The maximum distance from the control tower, at which structures can be controlled.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(15000)
    public int PosStructureControlDistanceMax;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiCapitalMissileResistance;
    /**
     * How many meters from the standard warp-in distance a moon can be anchored from.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MoonAnchorDistance;
    /**
     * Maximum range at which the scanner can lock a target.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxTargetRange;
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
     * Radar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanRadarStrength;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HullThermalDamageResonance;
    /**
     * Ladar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanLadarStrength;
    /**
     * Secondary cargo space allowed, meant to supplement capacity. This is currently used exclusively for Strontium storage on starbases.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int CapacitySecondary;
    /**
     * The interval for fuel consumption of a control tower
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int PosControlTowerPeriod;
    /**
     * Magnetometric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanMagnetometricStrength;
    /**
     * Gravimetric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanGravimetricStrength;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerLaserDamageBonus;
    /**
     * Autogenerated skill attribute, prototypingBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PrototypingBonus;
    /**
     * Amount of time taken to fully recharge the shield.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldRechargeRate;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldUniformity;
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
    public int ControlTowerProjectileOptimalBonus;
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
    public int ControlTowerProjectileROFBonus;
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
    public int ControlTowerSiloCapacityBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerHybridDamageBonus;
    public final static String RESOURCE_PATH = "SDE/items/starbase/ControlTower.yaml";
    private static LinkedHashMap<String, ControlTower> cache = (null);

    @Override
    public int getGroupId() {
        return  365;
    }

    @Override
    public Class<?> getGroup() {
        return ControlTower.class;
    }

    public static synchronized LinkedHashMap<String, ControlTower> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ControlTower.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, ControlTower> items;
    }
}
