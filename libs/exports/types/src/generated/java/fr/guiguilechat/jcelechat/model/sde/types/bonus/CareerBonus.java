package fr.guiguilechat.jcelechat.model.sde.types.bonus;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.CharismaSkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.IntelligenceSkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MemorySkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PerceptionSkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WillpowerSkillTrainingTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.types.Bonus;

public class CareerBonus
    extends Bonus
{
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
     * Bonus or penalty to the percentage time it takes to train skills with Willpower as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double willpowerskilltrainingtimemultiplierbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {CharismaSkillTrainingTimeMultiplierBonus.INSTANCE, IntelligenceSkillTrainingTimeMultiplierBonus.INSTANCE, MemorySkillTrainingTimeMultiplierBonus.INSTANCE, PerceptionSkillTrainingTimeMultiplierBonus.INSTANCE, WillpowerSkillTrainingTimeMultiplierBonus.INSTANCE })));
    public static final CareerBonus.MetaGroup METAGROUP = new CareerBonus.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  228 :
            {
                return charismaskilltrainingtimemultiplierbonus;
            }
            case  229 :
            {
                return intelligenceskilltrainingtimemultiplierbonus;
            }
            case  230 :
            {
                return memoryskilltrainingtimemultiplierbonus;
            }
            case  231 :
            {
                return perceptionskilltrainingtimemultiplierbonus;
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
        private Map<Integer, CareerBonus> cache = (null);

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
        public synchronized Map<Integer, CareerBonus> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CareerBonus.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, CareerBonus> types;
        }
    }
}
