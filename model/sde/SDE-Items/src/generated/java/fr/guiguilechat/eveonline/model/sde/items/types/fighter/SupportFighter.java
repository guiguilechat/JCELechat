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
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityECMDuration;
    /**
     * Effectiveness Falloff
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityECMRangeFalloff;
    /**
     * Optimal Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityECMRangeOptimal;
    /**
     * Gravimetric ECM Jammer Strength
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double FighterAbilityECMStrengthGravimetric;
    /**
     * Ladar ECM Jammer Strength
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double FighterAbilityECMStrengthLadar;
    /**
     * Magnetometric ECM Jammer Strength
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double FighterAbilityECMStrengthMagnetometric;
    /**
     * Radar ECM Jammer Strength
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double FighterAbilityECMStrengthRadar;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityECMTargetJam;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityECMTargetSuccess;
    /**
     * Energy Amount Neutralized
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityEnergyNeutralizerAmount;
    /**
     * Duration
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityEnergyNeutralizerDuration;
    /**
     * Effectiveness Falloff
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityEnergyNeutralizerFalloffRange;
    /**
     * Optimal Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityEnergyNeutralizerOptimalRange;
    /**
     * Duration
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityStasisWebifierDuration;
    /**
     * Effectiveness Falloff
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityStasisWebifierFalloffRange;
    /**
     * Optimal Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityStasisWebifierOptimalRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityStasisWebifierResistanceID;
    /**
     * Maximum Velocity Bonus
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityStasisWebifierSpeedPenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityStasisWebifierSpeedPenaltyInterim;
    /**
     * Duration
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityWarpDisruptionDuration;
    /**
     * Warp Disruption Strength
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityWarpDisruptionPointStrength;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityWarpDisruptionPointStrengthInterim;
    /**
     * Optimal Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityWarpDisruptionRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSquadronIsStandupSupport;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSquadronIsSupport;
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

    public static synchronized LinkedHashMap<String, SupportFighter> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SupportFighter.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SupportFighter> items;
    }
}
