package fr.guiguilechat.jcelechat.model.sde.types.skill;

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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanNotBeTrainedOnTrial;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.PrimaryAttribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SecondaryAttribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.SkillPoints;
import fr.guiguilechat.jcelechat.model.sde.attributes.SkillTimeConstant;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Sequencing
    extends Skill
{
    /**
     * If set to 1 then this skill can not be trained on accounts that are marked as Alpha Clone. Any other value (although you should probably use 0) will result in all accounts being able to train this skill.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int cannotbetrainedontrial;
    /**
     * Total accumulated points for skill
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int skillpoints;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, SkillTimeConstant.INSTANCE, SkillPoints.INSTANCE, PrimaryAttribute.INSTANCE, RequiredSkill1Level.INSTANCE, SecondaryAttribute.INSTANCE, Capacity.INSTANCE, RequiredSkill1 .INSTANCE, CanNotBeTrainedOnTrial.INSTANCE })));
    public static final Sequencing.MetaGroup METAGROUP = new Sequencing.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1047 :
            {
                return cannotbetrainedontrial;
            }
            case  276 :
            {
                return skillpoints;
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
    public IMetaGroup<Sequencing> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Sequencing>
    {
        public static final String RESOURCE_PATH = "SDE/types/skill/Sequencing.yaml";
        private Map<Integer, Sequencing> cache = (null);

        @Override
        public IMetaCategory<? super Sequencing> category() {
            return Skill.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4734;
        }

        @Override
        public String getName() {
            return "Sequencing";
        }

        @Override
        public synchronized Map<Integer, Sequencing> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Sequencing.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Sequencing> types;
        }
    }
}
