
package fr.guiguilechat.eveonline.model.sde.items.types.fighter;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Fighter;
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
    @DefaultIntValue(0)
    public int FighterAbilityStasisWebifierDuration;
    /**
     * Maximum Velocity Bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityStasisWebifierSpeedPenalty;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityStasisWebifierSpeedPenaltyInterim;
    /**
     * Optimal Range
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityStasisWebifierOptimalRange;
    /**
     * Effectiveness Falloff
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityStasisWebifierFalloffRange;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityStasisWebifierResistanceID;
    /**
     * Duration
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityWarpDisruptionDuration;
    /**
     * Optimal Range
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityWarpDisruptionRange;
    /**
     * Warp Disruption Strength
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityWarpDisruptionPointStrength;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityWarpDisruptionPointStrengthInterim;
    /**
     * Duration
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityEnergyNeutralizerDuration;
    /**
     * Optimal Range
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityEnergyNeutralizerOptimalRange;
    /**
     * Effectiveness Falloff
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityEnergyNeutralizerFalloffRange;
    /**
     * Energy Amount Neutralized
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityEnergyNeutralizerAmount;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSquadronIsSupport;
    /**
     * Duration
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityECMDuration;
    /**
     * Optimal Range
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityECMRangeOptimal;
    /**
     * Effectiveness Falloff
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityECMRangeFalloff;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSquadronIsStandupSupport;
    /**
     * Gravimetric ECM Jammer Strength
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double FighterAbilityECMStrengthGravimetric;
    /**
     * Ladar ECM Jammer Strength
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double FighterAbilityECMStrengthLadar;
    /**
     * Magnetometric ECM Jammer Strength
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double FighterAbilityECMStrengthMagnetometric;
    /**
     * Radar ECM Jammer Strength
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double FighterAbilityECMStrengthRadar;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityECMTargetSuccess;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityECMTargetJam;
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
