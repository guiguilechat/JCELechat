package fr.guiguilechat.eveonline.model.sde.items.types.skill;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Skill;
import org.yaml.snakeyaml.Yaml;

public class NeuralEnhancement
    extends Skill
{
    /**
     * Bonus to duration.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DurationBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BoosterChanceBonus;
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
    public int MaxJumpClonesBonus;
    /**
     * Time constant for skill training
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SkillTimeConstant;
    /**
     * The maximum amount of jump clones that the character may have in existence or ship may have stored.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxJumpClones;
    /**
     * Only refers to another dogma attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PrimaryAttribute;
    /**
     * Only refers to another dogma attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SecondaryAttribute;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2Level;
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
    public final static String RESOURCE_PATH = "SDE/items/skill/NeuralEnhancement.yaml";
    private static LinkedHashMap<String, NeuralEnhancement> cache = (null);

    @Override
    public int getGroupId() {
        return  1220;
    }

    @Override
    public Class<?> getGroup() {
        return NeuralEnhancement.class;
    }

    public static synchronized LinkedHashMap<String, NeuralEnhancement> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(NeuralEnhancement.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, NeuralEnhancement> items;
    }
}
