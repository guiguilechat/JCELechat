
package fr.guiguilechat.eveonline.model.sde.items.types.starbase;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Starbase;
import org.yaml.snakeyaml.Yaml;

public class WarpScramblingBattery
    extends Starbase
{

    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MaxLockedTargets;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.1D)
    public double FighterAbilityAntiCapitalMissileResistance;
    /**
     * Amount of maximum shield HP on the item.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShieldCapacity;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Uniformity;
    /**
     * Length of activation time.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Duration;
    /**
     * The number of hit points on the entities armor.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ArmorHP;
    /**
     * Maximum range at which the scanner can lock a target.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double MaxTargetRange;
    /**
     * DO NOT MESS WITH
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ArmorUniformity;
    /**
     * Minimum distance where a starbase structure can be anchored at from the control tower shield extremity in meters.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ControlTowerMinimumDistance;
    /**
     * If a starbase structure has this attribute = 1 then it can be controlled by owners with infrastructure tactical officer skill and corp role.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double PosPlayerControlStructure;
    /**
     * Radar strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double ScanRadarStrength;
    /**
     * Ladar strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double ScanLadarStrength;
    /**
     * Magnetometric strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double ScanMagnetometricStrength;
    /**
     * The chance of an entity attacking the same person as its group members.  Scales delay in joining in on fights too.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double EntityReactionFactor;
    /**
     * Gravimetric strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double ScanGravimetricStrength;
    /**
     * Required skill level for skill 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill1Level;
    /**
     * The distance at which to react when relevant objects come within range.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ProximityRange;
    /**
     * Minimum attack delay time for entity.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double EntityAttackDelayMin;
    /**
     * Maximum attack delay time for entity.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double EntityAttackDelayMax;
    /**
     * The hull damage proportion at which an entity becomes incapacitated.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double IncapacitationRatio;
    /**
     * meta group of type
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MetaGroupID;
    /**
     * current power need
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Power;
    /**
     * Amount of time taken to fully recharge the shield.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShieldRechargeRate;
    /**
     * The distance at which the entity orbits, follows.. and more.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(500.0D)
    public double EntityFlyRange;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShieldUniformity;
    /**
     * Maximum range objects can be warp scrambled from.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double WarpScrambleRange;
    /**
     * Signature Radius is used for turret tracking and scanning.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(100.0D)
    public double SignatureRadius;
    /**
     * Amount to modify ships warp scramble status by.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double WarpScrambleStrength;
    /**
     * damage multiplier vs. kinetic damagers.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double KineticDamageResonance;
    /**
     * damage multiplier vs. thermal.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double ThermalDamageResonance;
    /**
     * damage multiplier vs. explosive damagers.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double ExplosiveDamageResonance;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double EmDamageResonance;
    /**
     * CPU need of module
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Cpu;
    /**
     * The amount of time after attacking a target that an entity will wait before switching to a new one.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double TargetSwitchDelay;
    /**
     * The resolution that the vessel can target other objects at.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double ScanResolution;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill1;
    /**
     * Chance of entity warp scrambling it's target.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double EntityWarpScrambleChance;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(8000.0D)
    public double WarpScrambleDuration;
    public final static String RESOURCE_PATH = "SDE/items/starbase/WarpScramblingBattery.yaml";
    private static LinkedHashMap<String, WarpScramblingBattery> cache = (null);

    @Override
    public int getGroupId() {
        return  443;
    }

    @Override
    public Class<?> getGroup() {
        return WarpScramblingBattery.class;
    }

    public static LinkedHashMap<String, WarpScramblingBattery> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(WarpScramblingBattery.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, WarpScramblingBattery> items;

    }

}
