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

public class NeuralEnhancement
    extends Skill
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BoosterAttributeModifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BoosterChanceBonus;
    /**
     * If set to 1 on a skill then this skill can not be trained on accounts that are marked as Trial.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanNotBeTrainedOnTrial;
    /**
     * Bonus to duration.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DurationBonus;
    /**
     * The maximum amount of jump clones that the character may have in existence or ship may have stored.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxJumpClones;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxJumpClonesBonus;
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
    public static final NeuralEnhancement.MetaGroup METAGROUP = new NeuralEnhancement.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1126 :
            {
                return BoosterAttributeModifier;
            }
            case  1125 :
            {
                return BoosterChanceBonus;
            }
            case  1047 :
            {
                return CanNotBeTrainedOnTrial;
            }
            case  66 :
            {
                return DurationBonus;
            }
            case  979 :
            {
                return MaxJumpClones;
            }
            case  1073 :
            {
                return MaxJumpClonesBonus;
            }
            case  183 :
            {
                return RequiredSkill2;
            }
            case  278 :
            {
                return RequiredSkill2Level;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<NeuralEnhancement> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NeuralEnhancement>
    {
        public static final String RESOURCE_PATH = "SDE/types/skill/NeuralEnhancement.yaml";
        private Map<String, NeuralEnhancement> cache = (null);

        @Override
        public IMetaCategory<? super NeuralEnhancement> category() {
            return Skill.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1220;
        }

        @Override
        public String getName() {
            return "NeuralEnhancement";
        }

        @Override
        public synchronized Map<String, NeuralEnhancement> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(NeuralEnhancement.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NeuralEnhancement> types;
        }
    }
}
