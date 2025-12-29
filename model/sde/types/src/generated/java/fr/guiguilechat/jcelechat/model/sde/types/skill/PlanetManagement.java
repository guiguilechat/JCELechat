package fr.guiguilechat.jcelechat.model.sde.types.skill;

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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanNotBeTrainedOnTrial;
import fr.guiguilechat.jcelechat.model.sde.attributes.PrimaryAttribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SecondaryAttribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.SkillLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.SkillTimeConstant;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;

public class PlanetManagement
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2level;
    /**
     * Level of skill
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int skilllevel;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {SkillTimeConstant.INSTANCE, PrimaryAttribute.INSTANCE, SecondaryAttribute.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill1 .INSTANCE, CanNotBeTrainedOnTrial.INSTANCE, RequiredSkill2 .INSTANCE, SkillLevel.INSTANCE })));
    public static final PlanetManagement.MetaGroup METAGROUP = new PlanetManagement.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1047 :
            {
                return cannotbetrainedontrial;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  280 :
            {
                return skilllevel;
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
    public IMetaGroup<PlanetManagement> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PlanetManagement>
    {
        public static final String RESOURCE_PATH = "SDE/types/skill/PlanetManagement.yaml";
        private Map<Integer, PlanetManagement> cache = (null);

        @Override
        public IMetaCategory<? super PlanetManagement> category() {
            return Skill.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1241;
        }

        @Override
        public String getName() {
            return "PlanetManagement";
        }

        @Override
        public synchronized Map<Integer, PlanetManagement> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PlanetManagement.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, PlanetManagement> types;
        }
    }
}
