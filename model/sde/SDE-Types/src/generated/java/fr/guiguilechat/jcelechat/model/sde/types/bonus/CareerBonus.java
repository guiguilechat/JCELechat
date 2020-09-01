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
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CharismaSkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.IntelligenceSkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MemorySkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PerceptionSkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.WillpowerSkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.types.Bonus;
import org.yaml.snakeyaml.Yaml;

public class CareerBonus
    extends Bonus
{
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
     * Bonus or penalty to the percentage time it takes to train skills with Intelligence as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double intelligenceskilltrainingtimemultiplierbonus;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double mass;
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
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Willpower as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double willpowerskilltrainingtimemultiplierbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Mass.INSTANCE, CharismaSkillTrainingTimeMultiplierBonus.INSTANCE, IntelligenceSkillTrainingTimeMultiplierBonus.INSTANCE, Capacity.INSTANCE, MemorySkillTrainingTimeMultiplierBonus.INSTANCE, PerceptionSkillTrainingTimeMultiplierBonus.INSTANCE, WillpowerSkillTrainingTimeMultiplierBonus.INSTANCE })));
    public static final CareerBonus.MetaGroup METAGROUP = new CareerBonus.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  38 :
            {
                return capacity;
            }
            case  228 :
            {
                return charismaskilltrainingtimemultiplierbonus;
            }
            case  229 :
            {
                return intelligenceskilltrainingtimemultiplierbonus;
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
            case  232 :
            {
                return willpowerskilltrainingtimemultiplierbonus;
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
    public IMetaGroup<CareerBonus> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CareerBonus>
    {
        public static final String RESOURCE_PATH = "SDE/types/bonus/CareerBonus.yaml";
        private Map<String, CareerBonus> cache = (null);

        @Override
        public IMetaCategory<? super CareerBonus> category() {
            return Bonus.METACAT;
        }

        @Override
        public int getGroupId() {
            return  199;
        }

        @Override
        public String getName() {
            return "CareerBonus";
        }

        @Override
        public synchronized Map<String, CareerBonus> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CareerBonus.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CareerBonus> types;
        }
    }
}
