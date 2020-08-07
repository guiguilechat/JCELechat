package fr.guiguilechat.jcelechat.model.sde.types.bonus;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CharismaSkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MemorySkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PerceptionSkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.Bonus;
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
    public double capacitorneedmultiplier;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double capacity;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Charisma as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double charismaskilltrainingtimemultiplierbonus;
    /**
     * Factor to adjust module cpu need by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double cpumultiplier;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double mass;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Memory as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double memoryskilltrainingtimemultiplierbonus;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Perception as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double perceptionskilltrainingtimemultiplierbonus;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double radius;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Mass.INSTANCE, CharismaSkillTrainingTimeMultiplierBonus.INSTANCE, Capacity.INSTANCE, MemorySkillTrainingTimeMultiplierBonus.INSTANCE, PerceptionSkillTrainingTimeMultiplierBonus.INSTANCE, CapacitorNeedMultiplier.INSTANCE, CpuMultiplier.INSTANCE })));
    public static final GallenteEducation.MetaGroup METAGROUP = new GallenteEducation.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  216 :
            {
                return capacitorneedmultiplier;
            }
            case  38 :
            {
                return capacity;
            }
            case  228 :
            {
                return charismaskilltrainingtimemultiplierbonus;
            }
            case  202 :
            {
                return cpumultiplier;
            }
            case  4 :
            {
                return mass;
            }
            case  230 :
            {
                return memoryskilltrainingtimemultiplierbonus;
            }
            case  231 :
            {
                return perceptionskilltrainingtimemultiplierbonus;
            }
            case  162 :
            {
                return radius;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<GallenteEducation> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<GallenteEducation>
    {
        public static final String RESOURCE_PATH = "SDE/types/bonus/GallenteEducation.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(GallenteEducation.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, GallenteEducation> types;
        }
    }
}
