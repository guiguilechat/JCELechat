
package fr.guiguilechat.eveonline.model.sde.compiled.items.fighter;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Fighter;
import org.yaml.snakeyaml.Yaml;

public class SupportFighter
    extends Fighter
{

    /**
     * Duration
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityStasisWebifierDuration;
    /**
     * Maximum Velocity Bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityStasisWebifierSpeedPenalty;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityStasisWebifierSpeedPenaltyInterim;
    /**
     * Optimal Range
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityStasisWebifierOptimalRange;
    /**
     * Effectiveness Falloff
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityStasisWebifierFalloffRange;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityStasisWebifierResistanceID;
    /**
     * Duration
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityWarpDisruptionDuration;
    /**
     * Optimal Range
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityWarpDisruptionRange;
    /**
     * Warp Disruption Strength
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityWarpDisruptionPointStrength;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityWarpDisruptionPointStrengthInterim;
    /**
     * Duration
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityEnergyNeutralizerDuration;
    /**
     * Optimal Range
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityEnergyNeutralizerOptimalRange;
    /**
     * Effectiveness Falloff
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityEnergyNeutralizerFalloffRange;
    /**
     * Energy Amount Neutralized
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityEnergyNeutralizerAmount;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterSquadronIsSupport;
    /**
     * Duration
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityECMDuration;
    /**
     * Optimal Range
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityECMRangeOptimal;
    /**
     * Effectiveness Falloff
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityECMRangeFalloff;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterSquadronIsStandupSupport;
    /**
     * Gravimetric ECM Jammer Strength
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityECMStrengthGravimetric;
    /**
     * Ladar ECM Jammer Strength
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityECMStrengthLadar;
    /**
     * Magnetometric ECM Jammer Strength
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityECMStrengthMagnetometric;
    /**
     * Radar ECM Jammer Strength
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityECMStrengthRadar;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityECMTargetSuccess;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityECMTargetJam;
    public final static String RESOURCE_PATH = "SDE/items/fighter/SupportFighter.yaml";
    private static LinkedHashMap<String, SupportFighter> cache = (null);

    @Override
    public int getGroupId() {
        return  1537;
    }

    @Override
    public Class<?> getGroup() {
        return SupportFighter.class;
    }

    public static LinkedHashMap<String, SupportFighter> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SupportFighter.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SupportFighter> items;

    }

}
