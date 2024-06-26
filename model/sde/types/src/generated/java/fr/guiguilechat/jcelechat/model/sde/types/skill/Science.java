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
import fr.guiguilechat.jcelechat.model.sde.attributes.BlueprintmanufactureTimeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanNotBeTrainedOnTrial;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CopySpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsSkillIObsolete;
import fr.guiguilechat.jcelechat.model.sde.attributes.LaboratorySlotsBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ManufactureTimePerLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxAttackTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MineralNeedResearchBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PrimaryAttribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ResearchGangSizeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SecondaryAttribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.SkillLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.SkillTimeConstant;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Science
    extends Skill
{
    /**
     * Autogenerated skill attribute, blueprintmanufactureTimeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int blueprintmanufacturetimebonus;
    /**
     * If set to 1 then this skill can not be trained on accounts that are marked as Alpha Clone. Any other value (although you should probably use 0) will result in all accounts being able to train this skill.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int cannotbetrainedontrial;
    /**
     * Autogenerated skill attribute, CopySpeedBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int copyspeedbonus;
    /**
     * Autogenerated skill attribute, inventionBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int inventionbonus;
    /**
     * When set True, skill can no longer be injected or trained. Characters will be reimbursed with free SP for any obsolete skills in the skill queue upon logon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int isskilliobsolete;
    /**
     * Autogenerated skill attribute, laboratorySlotsBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int laboratoryslotsbonus;
    /**
     * Skill bonus per level to manufacturing time efficiency. Only applies to skills required to manufacture the blueprint.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int manufacturetimeperlevel;
    /**
     * The maximum number of their targets that the character can attack at a given time.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxattacktargets;
    /**
     * Autogenerated skill attribute, mineralNeedResearchBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int mineralneedresearchbonus;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill3;
    /**
     * Required skill level for skill 3
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill3level;
    /**
     * Max research gang size bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int researchgangsizebonus;
    /**
     * Level of skill
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int skilllevel;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {MaxAttackTargets.INSTANCE, Radius.INSTANCE, CopySpeedBonus.INSTANCE, BlueprintmanufactureTimeBonus.INSTANCE, Capacity.INSTANCE, IsSkillIObsolete.INSTANCE, SkillTimeConstant.INSTANCE, MineralNeedResearchBonus.INSTANCE, PrimaryAttribute.INSTANCE, RequiredSkill1Level.INSTANCE, SecondaryAttribute.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, CanNotBeTrainedOnTrial.INSTANCE, LaboratorySlotsBonus.INSTANCE, RequiredSkill3Level.INSTANCE, ResearchGangSizeBonus.INSTANCE, SkillLevel.INSTANCE, RequiredSkill3 .INSTANCE, InventionBonus.INSTANCE, ManufactureTimePerLevel.INSTANCE })));
    public static final Science.MetaGroup METAGROUP = new Science.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  453 :
            {
                return blueprintmanufacturetimebonus;
            }
            case  1047 :
            {
                return cannotbetrainedontrial;
            }
            case  452 :
            {
                return copyspeedbonus;
            }
            case  474 :
            {
                return inventionbonus;
            }
            case  2450 :
            {
                return isskilliobsolete;
            }
            case  471 :
            {
                return laboratoryslotsbonus;
            }
            case  1982 :
            {
                return manufacturetimeperlevel;
            }
            case  193 :
            {
                return maxattacktargets;
            }
            case  468 :
            {
                return mineralneedresearchbonus;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  184 :
            {
                return requiredskill3;
            }
            case  279 :
            {
                return requiredskill3level;
            }
            case  407 :
            {
                return researchgangsizebonus;
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
    public IMetaGroup<Science> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Science>
    {
        public static final String RESOURCE_PATH = "SDE/types/skill/Science.yaml";
        private Map<Integer, Science> cache = (null);

        @Override
        public IMetaCategory<? super Science> category() {
            return Skill.METACAT;
        }

        @Override
        public int getGroupId() {
            return  270;
        }

        @Override
        public String getName() {
            return "Science";
        }

        @Override
        public synchronized Map<Integer, Science> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Science.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Science> types;
        }
    }
}
