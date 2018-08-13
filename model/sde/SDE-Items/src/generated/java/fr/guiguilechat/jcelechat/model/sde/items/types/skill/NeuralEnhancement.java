package fr.guiguilechat.jcelechat.model.sde.items.types.skill;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Skill;
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
    public final static String RESOURCE_PATH = "SDE/items/skill/NeuralEnhancement.yaml";
    private static Map<String, NeuralEnhancement> cache = (null);

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
    public int getGroupId() {
        return  1220;
    }

    @Override
    public Class<?> getGroup() {
        return NeuralEnhancement.class;
    }

    public static synchronized Map<String, NeuralEnhancement> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(NeuralEnhancement.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, NeuralEnhancement> items;
    }
}
