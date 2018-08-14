package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class DataMiners
    extends Module
{
    /**
     * Bonus to chance of opening a container.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AccessDifficultyBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipType1;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorNeed;
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
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxRange;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2Level;
    /**
     * The coherence of a virus.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int VirusCoherence;
    /**
     * The number of utility element slots a virus has.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int VirusElementSlots;
    /**
     * The strength attribute for a Virus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int VirusStrength;
    public final static DataMiners.MetaGroup METAGROUP = new DataMiners.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  902 :
            {
                return AccessDifficultyBonus;
            }
            case  1302 :
            {
                return CanFitShipType1;
            }
            case  6 :
            {
                return CapacitorNeed;
            }
            case  50 :
            {
                return Cpu;
            }
            case  73 :
            {
                return Duration;
            }
            case  54 :
            {
                return MaxRange;
            }
            case  1692 :
            {
                return MetaGroupID;
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
            case  183 :
            {
                return RequiredSkill2;
            }
            case  278 :
            {
                return RequiredSkill2Level;
            }
            case  1909 :
            {
                return VirusCoherence;
            }
            case  1911 :
            {
                return VirusElementSlots;
            }
            case  1910 :
            {
                return VirusStrength;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<DataMiners> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DataMiners>
    {
        public final static String RESOURCE_PATH = "SDE/items/module/DataMiners.yaml";
        private Map<String, DataMiners> cache = (null);

        @Override
        public IMetaCategory<? super DataMiners> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  538;
        }

        @Override
        public String getName() {
            return "DataMiners";
        }

        @Override
        public synchronized Map<String, DataMiners> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DataMiners.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DataMiners> items;
        }
    }
}
