package fr.guiguilechat.jcelechat.model.sde.types.skill;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import org.yaml.snakeyaml.Yaml;

public class Subsystems
    extends Skill
{
    /**
     * If set to 1 on a skill then this skill can not be trained on accounts that are marked as Trial.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanNotBeTrainedOnTrial;
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
     * When set True, skill can no longer be injected or trained. Characters will be reimbursed with free SP for any obsolete skills in the skill queue upon logon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SkillIsObsolete;
    public static final Subsystems.MetaGroup METAGROUP = new Subsystems.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1047 :
            {
                return CanNotBeTrainedOnTrial;
            }
            case  183 :
            {
                return RequiredSkill2;
            }
            case  278 :
            {
                return RequiredSkill2Level;
            }
            case  2450 :
            {
                return SkillIsObsolete;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<Subsystems> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Subsystems>
    {
        public static final String RESOURCE_PATH = "SDE/types/skill/Subsystems.yaml";
        private Map<String, Subsystems> cache = (null);

        @Override
        public IMetaCategory<? super Subsystems> category() {
            return Skill.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1240;
        }

        @Override
        public String getName() {
            return "Subsystems";
        }

        @Override
        public synchronized Map<String, Subsystems> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Subsystems.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Subsystems> types;
        }
    }
}
