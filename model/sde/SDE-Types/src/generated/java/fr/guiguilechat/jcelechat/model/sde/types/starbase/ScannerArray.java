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

public class ScannerArray
    extends Starbase
{
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Duration;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiCapitalMissileResistance;
    /**
     * The hull damage proportion at which an entity becomes incapacitated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double IncapacitationRatio;
    /**
     * Maximum scan error
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double MaxScanDeviation;
    /**
     * Minimum scanning error
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MinScanDeviation;
    /**
     * Whether the structure requires the onliner's alliance to hold sovereignty in the system for it to be onlineable.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int OnliningRequiresSovereigntyLevel;
    /**
     * How many structures in this group can be anchored for the same alliance per solar system.  0 means there is no limit.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PosAnchoredPerSolarSystemAmount;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * Effective range of scanner in multiples of AUs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10)
    public int ScanRange;
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
    public static final ScannerArray.MetaGroup METAGROUP = new ScannerArray.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  50 :
            {
                return Cpu;
            }
            case  73 :
            {
                return Duration;
            }
            case  2244 :
            {
                return FighterAbilityAntiCapitalMissileResistance;
            }
            case  156 :
            {
                return IncapacitationRatio;
            }
            case  788 :
            {
                return MaxScanDeviation;
            }
            case  787 :
            {
                return MinScanDeviation;
            }
            case  1185 :
            {
                return OnliningRequiresSovereigntyLevel;
            }
            case  1195 :
            {
                return PosAnchoredPerSolarSystemAmount;
            }
            case  30 :
            {
                return Power;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  765 :
            {
                return ScanRange;
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
    public IMetaGroup<ScannerArray> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ScannerArray>
    {
        public static final String RESOURCE_PATH = "SDE/types/starbase/ScannerArray.yaml";
        private Map<String, ScannerArray> cache = (null);

        @Override
        public IMetaCategory<? super ScannerArray> category() {
            return Starbase.METACAT;
        }

        @Override
        public int getGroupId() {
            return  709;
        }

        @Override
        public String getName() {
            return "ScannerArray";
        }

        @Override
        public synchronized Map<String, ScannerArray> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ScannerArray.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ScannerArray> types;
        }
    }
}
