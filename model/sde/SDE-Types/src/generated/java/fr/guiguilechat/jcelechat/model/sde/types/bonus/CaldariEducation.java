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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CharismaSkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MemorySkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PerceptionSkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.Bonus;
import org.yaml.snakeyaml.Yaml;

public class CaldariEducation
    extends Bonus
{
    /**
     * Scales the capacitor need for fitted modules.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorneedmultiplier;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Charisma as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double charismaskilltrainingtimemultiplierbonus;
    /**
     * Factor to adjust module cpu need by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double cpumultiplier;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Memory as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double memoryskilltrainingtimemultiplierbonus;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Perception as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double perceptionskilltrainingtimemultiplierbonus;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, CharismaSkillTrainingTimeMultiplierBonus.INSTANCE, Capacity.INSTANCE, MemorySkillTrainingTimeMultiplierBonus.INSTANCE, PerceptionSkillTrainingTimeMultiplierBonus.INSTANCE, CapacitorNeedMultiplier.INSTANCE, CpuMultiplier.INSTANCE })));
    public static final CaldariEducation.MetaGroup METAGROUP = new CaldariEducation.MetaGroup();

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
    public IMetaGroup<CaldariEducation> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CaldariEducation>
    {
        public static final String RESOURCE_PATH = "SDE/types/bonus/CaldariEducation.yaml";
        private Map<String, CaldariEducation> cache = (null);

        @Override
        public IMetaCategory<? super CaldariEducation> category() {
            return Bonus.METACAT;
        }

        @Override
        public int getGroupId() {
            return  196;
        }

        @Override
        public String getName() {
            return "CaldariEducation";
        }

        @Override
        public synchronized Map<String, CaldariEducation> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CaldariEducation.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CaldariEducation> types;
        }
    }
}
