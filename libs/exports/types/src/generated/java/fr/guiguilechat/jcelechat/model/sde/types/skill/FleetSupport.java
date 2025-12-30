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
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;

public class FleetSupport
    extends Skill
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int areaofeffectbonus;
    /**
     * If set to 1 then this skill can not be trained on accounts that are marked as Alpha Clone. Any other value (although you should probably use 0) will result in all accounts being able to train this skill.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int cannotbetrainedontrial;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int commandstrengthbonus;
    /**
     * Bonus to duration.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int durationbonus;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fleetcompressionlogisticsrangebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int reloadtimebonus;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {DurationBonus.INSTANCE, CommandStrengthBonus.INSTANCE, ReloadTimeBonus.INSTANCE, SkillTimeConstant.INSTANCE, PrimaryAttribute.INSTANCE, SecondaryAttribute.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill1 .INSTANCE, CanNotBeTrainedOnTrial.INSTANCE, RequiredSkill2 .INSTANCE, SkillLevel.INSTANCE, AreaOfEffectBonus.INSTANCE, FleetCompressionLogisticsRangeBonus.INSTANCE })));
    public static final FleetSupport.MetaGroup METAGROUP = new FleetSupport.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  670 :
            {
                return areaofeffectbonus;
            }
            case  1047 :
            {
                return cannotbetrainedontrial;
            }
            case  2572 :
            {
                return commandstrengthbonus;
            }
            case  66 :
            {
                return durationbonus;
            }
            case  3263 :
            {
                return fleetcompressionlogisticsrangebonus;
            }
            case  2573 :
            {
                return reloadtimebonus;
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
    public IMetaGroup<FleetSupport> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<FleetSupport>
    {
        public static final String RESOURCE_PATH = "SDE/types/skill/FleetSupport.yaml";
        private Map<Integer, FleetSupport> cache = (null);

        @Override
        public IMetaCategory<? super FleetSupport> category() {
            return Skill.METACAT;
        }

        @Override
        public int getGroupId() {
            return  258;
        }

        @Override
        public String getName() {
            return "FleetSupport";
        }

        @Override
        public synchronized Map<Integer, FleetSupport> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(FleetSupport.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, FleetSupport> types;
        }
    }
}
