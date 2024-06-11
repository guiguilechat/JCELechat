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
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterAttributeModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterChanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanNotBeTrainedOnTrial;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.DurationBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxJumpClones;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxJumpClonesBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PrimaryAttribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SecondaryAttribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.SkillLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.SkillTimeConstant;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import org.yaml.snakeyaml.LoaderOptions;
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
    public int boosterattributemodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boosterchancebonus;
    /**
     * If set to 1 then this skill can not be trained on accounts that are marked as Alpha Clone. Any other value (although you should probably use 0) will result in all accounts being able to train this skill.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int cannotbetrainedontrial;
    /**
     * Bonus to duration.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int durationbonus;
    /**
     * The maximum amount of jump clones that the character may have in existence or ship may have stored.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxjumpclones;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxjumpclonesbonus;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {DurationBonus.INSTANCE, Radius.INSTANCE, BoosterChanceBonus.INSTANCE, BoosterAttributeModifier.INSTANCE, Capacity.INSTANCE, MaxJumpClonesBonus.INSTANCE, MaxJumpClones.INSTANCE, SkillTimeConstant.INSTANCE, PrimaryAttribute.INSTANCE, RequiredSkill1Level.INSTANCE, SecondaryAttribute.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill1 .INSTANCE, CanNotBeTrainedOnTrial.INSTANCE, RequiredSkill2 .INSTANCE, SkillLevel.INSTANCE })));
    public static final NeuralEnhancement.MetaGroup METAGROUP = new NeuralEnhancement.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1126 :
            {
                return boosterattributemodifier;
            }
            case  1125 :
            {
                return boosterchancebonus;
            }
            case  1047 :
            {
                return cannotbetrainedontrial;
            }
            case  66 :
            {
                return durationbonus;
            }
            case  979 :
            {
                return maxjumpclones;
            }
            case  1073 :
            {
                return maxjumpclonesbonus;
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
    public IMetaGroup<NeuralEnhancement> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NeuralEnhancement>
    {
        public static final String RESOURCE_PATH = "SDE/types/skill/NeuralEnhancement.yaml";
        private Map<Integer, NeuralEnhancement> cache = (null);

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
        public synchronized Map<Integer, NeuralEnhancement> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(NeuralEnhancement.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, NeuralEnhancement> types;
        }
    }
}
