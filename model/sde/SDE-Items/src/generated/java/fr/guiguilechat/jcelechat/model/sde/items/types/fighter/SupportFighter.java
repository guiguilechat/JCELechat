package fr.guiguilechat.jcelechat.model.sde.items.types.fighter;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Fighter;

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
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2220 :
            {
                return FighterAbilityECMDuration;
            }
            case  2222 :
            {
                return FighterAbilityECMRangeFalloff;
            }
            case  2221 :
            {
                return FighterAbilityECMRangeOptimal;
            }
            case  2246 :
            {
                return FighterAbilityECMStrengthGravimetric;
            }
            case  2247 :
            {
                return FighterAbilityECMStrengthLadar;
            }
            case  2248 :
            {
                return FighterAbilityECMStrengthMagnetometric;
            }
            case  2249 :
            {
                return FighterAbilityECMStrengthRadar;
            }
            case  2251 :
            {
                return FighterAbilityECMTargetJam;
            }
            case  2250 :
            {
                return FighterAbilityECMTargetSuccess;
            }
            case  2211 :
            {
                return FighterAbilityEnergyNeutralizerAmount;
            }
            case  2208 :
            {
                return FighterAbilityEnergyNeutralizerDuration;
            }
            case  2210 :
            {
                return FighterAbilityEnergyNeutralizerFalloffRange;
            }
            case  2209 :
            {
                return FighterAbilityEnergyNeutralizerOptimalRange;
            }
            case  2183 :
            {
                return FighterAbilityStasisWebifierDuration;
            }
            case  2187 :
            {
                return FighterAbilityStasisWebifierFalloffRange;
            }
            case  2186 :
            {
                return FighterAbilityStasisWebifierOptimalRange;
            }
            case  2188 :
            {
                return FighterAbilityStasisWebifierResistanceID;
            }
            case  2184 :
            {
                return FighterAbilityStasisWebifierSpeedPenalty;
            }
            case  2185 :
            {
                return FighterAbilityStasisWebifierSpeedPenaltyInterim;
            }
            case  2203 :
            {
                return FighterAbilityWarpDisruptionDuration;
            }
            case  2205 :
            {
                return FighterAbilityWarpDisruptionPointStrength;
            }
            case  2206 :
            {
                return FighterAbilityWarpDisruptionPointStrengthInterim;
            }
            case  2204 :
            {
                return FighterAbilityWarpDisruptionRange;
            }
            case  2741 :
            {
                return FighterSquadronIsStandupSupport;
            }
            case  2213 :
            {
                return FighterSquadronIsSupport;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

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
