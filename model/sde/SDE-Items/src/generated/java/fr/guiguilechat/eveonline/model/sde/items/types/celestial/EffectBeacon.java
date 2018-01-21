
package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class EffectBeacon
    extends Celestial
{

    /**
     * Bonus to duration.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DurationBonus;
    /**
     * Multiplys the damage multiplier
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DamageMultiplierMultiplier;
    /**
     * Multiplier to missiles ability to hit fast targets
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double AoeVelocityMultiplier;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double SignatureRadiusMultiplier;
    /**
     * Damage multiplier for heat
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double HeatDamageMultiplier;
    /**
     * Multiplier to all overload bonuses
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double OverloadBonusMultiplier;
    /**
     * Smart bomb range multiplier
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double EmpFieldRangeMultiplier;
    /**
     * Damage multiplier for smart bombs
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double SmartbombDamageMultiplier;
    /**
     * EM resistance bonus for shields
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShieldEmDamageResistanceBonus;
    /**
     * Explosive resistance bonus for shields
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShieldExplosiveDamageResistanceBonus;
    /**
     * Multiplier to the capacity of a shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double ShieldCapacityMultiplier;
    /**
     * Kinetic resistance bonus for shields
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShieldKineticDamageResistanceBonus;
    /**
     * Thermal resistance bonus for shields
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShieldThermalDamageResistanceBonus;
    /**
     * Multiplier to the HP of a ships armor module.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double ArmorHPMultiplier;
    /**
     * Factor by which topspeed increases.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double SpeedFactor;
    /**
     * Damage multiplier for small weapons
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double SmallWeaponDamageMultiplier;
    /**
     * Damage reduction for system effects
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double SystemEffectDamageReduction;
    /**
     * Armor repair amount multiplier
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ArmorDamageAmountMultiplier;
    /**
     * Shield transfer amount multiplier
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShieldBonusMultiplier;
    /**
     * Shield repair multiplier for remote repair
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShieldBonusMultiplierRemote;
    /**
     * Repair amount multiplier for remote repairers
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ArmorDamageAmountMultiplierRemote;
    /**
     * Capacitor capacity multiplier
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CapacitorCapacityMultiplierSystem;
    /**
     * Capacitor recharge rate multiplier
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RechargeRateMultiplier;
    /**
     * Multiplier to the agility of an object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double AgilityMultiplier;
    /**
     * Scales the max target range of a ships electronics.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double MaxTargetRangeMultiplier;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double EnergyWarfareStrengthMultiplier;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double AoeCloudSizeMultiplier;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double EnergyTransferAmountBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double TargetPainterStrengthMultiplier;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double StasisWebStrengthMultiplier;
    /**
     * Autogenerated skill attribute, mMaxVelocityBonus
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double MaxVelocityModifier;
    /**
     * Scale the tracking speed of a weapon.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double TrackingSpeedMultiplier;
    /**
     * Bonus to Max Targeting Range
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MaxTargetRangeBonus;
    /**
     * EM damage resistance bonus for armor
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ArmorEmDamageResistanceBonus;
    /**
     * Kinetic damage resistance bonus for armor
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ArmorKineticDamageResistanceBonus;
    /**
     * Thermal damage resistance bonus for armor
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ArmorThermalDamageResistanceBonus;
    /**
     * Explosive damage resistance bonus for armor
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ArmorExplosiveDamageResistanceBonus;
    /**
     * Velocity multiplier for missiles
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MissileVelocityMultiplier;
    /**
     * Maximum velocity multiplier
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MaxVelocityMultiplier;
    /**
     * Tracking Speed Bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double TrackingSpeedBonus;
    public final static String RESOURCE_PATH = "SDE/items/celestial/EffectBeacon.yaml";
    private static LinkedHashMap<String, EffectBeacon> cache = (null);

    @Override
    public int getGroupId() {
        return  920;
    }

    @Override
    public Class<?> getGroup() {
        return EffectBeacon.class;
    }

    public static LinkedHashMap<String, EffectBeacon> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(EffectBeacon.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, EffectBeacon> items;

    }

}
