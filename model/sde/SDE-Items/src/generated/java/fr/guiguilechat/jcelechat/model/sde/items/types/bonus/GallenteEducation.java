package fr.guiguilechat.jcelechat.model.sde.items.types.bonus;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Bonus;
import org.yaml.snakeyaml.Yaml;

public class GallenteEducation
    extends Bonus
{
    /**
     * Scales the capacitor need for fitted modules.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorNeedMultiplier;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Charisma as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CharismaSkillTrainingTimeMultiplierBonus;
    /**
     * Factor to adjust module cpu need by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double CpuMultiplier;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Memory as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double MemorySkillTrainingTimeMultiplierBonus;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Perception as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double PerceptionSkillTrainingTimeMultiplierBonus;
    public static final GallenteEducation.MetaGroup METAGROUP = new GallenteEducation.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  216 :
            {
                return CapacitorNeedMultiplier;
            }
            case  228 :
            {
                return CharismaSkillTrainingTimeMultiplierBonus;
            }
            case  202 :
            {
                return CpuMultiplier;
            }
            case  230 :
            {
                return MemorySkillTrainingTimeMultiplierBonus;
            }
            case  231 :
            {
                return PerceptionSkillTrainingTimeMultiplierBonus;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<GallenteEducation> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<GallenteEducation>
    {
        public static final String RESOURCE_PATH = "SDE/items/bonus/GallenteEducation.yaml";
        private Map<String, GallenteEducation> cache = (null);

        @Override
        public IMetaCategory<? super GallenteEducation> category() {
            return Bonus.METACAT;
        }

        @Override
        public int getGroupId() {
            return  197;
        }

        @Override
        public String getName() {
            return "GallenteEducation";
        }

        @Override
        public synchronized Map<String, GallenteEducation> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(GallenteEducation.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, GallenteEducation> items;
        }
    }
}
