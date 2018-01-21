
package fr.guiguilechat.eveonline.model.sde.items.types.starbase;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Starbase;
import org.yaml.snakeyaml.Yaml;

public class TargetPaintingBattery
    extends Starbase
{

    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxLockedTargets;
    /**
     * Amount of maximum shield HP on the item.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldCapacity;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double Uniformity;
    /**
     * The number of hit points on the entities armor.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorHP;
    /**
     * Length of activation time.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double Duration;
    /**
     * Maximum range at which the scanner can lock a target.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxTargetRange;
    /**
     * DO NOT MESS WITH
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ArmorUniformity;
    /**
     * Minimum distance where a starbase structure can be anchored at from the control tower shield extremity in meters.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ControlTowerMinimumDistance;
    /**
     * If a starbase structure has this attribute = 1 then it can be controlled by owners with infrastructure tactical officer skill and corp role.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PosPlayerControlStructure;
    /**
     * Radar strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanRadarStrength;
    /**
     * Ladar strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanLadarStrength;
    /**
     * Magnetometric strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanMagnetometricStrength;
    /**
     * The chance of an entity attacking the same person as its group members.  Scales delay in joining in on fights too.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double EntityReactionFactor;
    /**
     * Gravimetric strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanGravimetricStrength;
    /**
     * Required skill level for skill 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * The distance at which to react when relevant objects come within range.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ProximityRange;
    /**
     * Minimum attack delay time for entity.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityAttackDelayMin;
    /**
     * Maximum attack delay time for entity.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityAttackDelayMax;
    /**
     * The hull damage proportion at which an entity becomes incapacitated.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double IncapacitationRatio;
    /**
     * distance from maximum range at which accuracy has fallen by half
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int Falloff;
    /**
     * current power need
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * Amount of time taken to fully recharge the shield.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ShieldRechargeRate;
    /**
     * The distance at which the entity orbits, follows.. and more.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(500.0D)
    public double EntityFlyRange;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ShieldUniformity;
    /**
     * Signature Radius is used for turret tracking and scanning.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    /**
     * Autogenerated skill attribute, signatureRadiusBonus
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double SignatureRadiusBonus;
    /**
     * damage multiplier vs. kinetic damagers.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double KineticDamageResonance;
    /**
     * damage multiplier vs. thermal.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ThermalDamageResonance;
    /**
     * damage multiplier vs. explosive damagers.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ExplosiveDamageResonance;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double EmDamageResonance;
    /**
     * CPU need of module
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double Cpu;
    /**
     * The amount of time after attacking a target that an entity will wait before switching to a new one.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TargetSwitchDelay;
    /**
     * The resolution that the vessel can target other objects at.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanResolution;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Distance below which range does not affect the to-hit equation.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxRange;
    public final static String RESOURCE_PATH = "SDE/items/starbase/TargetPaintingBattery.yaml";
    private static LinkedHashMap<String, TargetPaintingBattery> cache = (null);

    @Override
    public int getGroupId() {
        return  877;
    }

    @Override
    public Class<?> getGroup() {
        return TargetPaintingBattery.class;
    }

    public static LinkedHashMap<String, TargetPaintingBattery> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(TargetPaintingBattery.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, TargetPaintingBattery> items;

    }

}
