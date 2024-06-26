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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PrimaryAttribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolutionBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SecondaryAttribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.SensorStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SkillLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.SkillTimeConstant;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Targeting
    extends Skill
{
    /**
     * Autogenerated skill attribute, maxTarget Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxtargetbonus;
    /**
     * Bonus to Max Targeting Range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double maxtargetrangebonus;
    /**
     * Bonus for scan resolution
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolutionbonus;
    /**
     * Bonus for Sensor Strength
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int sensorstrengthbonus;
    /**
     * Level of skill
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int skilllevel;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, SkillTimeConstant.INSTANCE, PrimaryAttribute.INSTANCE, MaxTargetRangeBonus.INSTANCE, RequiredSkill1Level.INSTANCE, SecondaryAttribute.INSTANCE, ScanResolutionBonus.INSTANCE, Capacity.INSTANCE, RequiredSkill1 .INSTANCE, MaxTargetBonus.INSTANCE, SkillLevel.INSTANCE, SensorStrengthBonus.INSTANCE })));
    public static final Targeting.MetaGroup METAGROUP = new Targeting.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  311 :
            {
                return maxtargetbonus;
            }
            case  309 :
            {
                return maxtargetrangebonus;
            }
            case  566 :
            {
                return scanresolutionbonus;
            }
            case  1851 :
            {
                return sensorstrengthbonus;
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
    public IMetaGroup<Targeting> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Targeting>
    {
        public static final String RESOURCE_PATH = "SDE/types/skill/Targeting.yaml";
        private Map<Integer, Targeting> cache = (null);

        @Override
        public IMetaCategory<? super Targeting> category() {
            return Skill.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1213;
        }

        @Override
        public String getName() {
            return "Targeting";
        }

        @Override
        public synchronized Map<Integer, Targeting> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Targeting.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Targeting> types;
        }
    }
}
