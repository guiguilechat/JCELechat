package fr.guiguilechat.jcelechat.model.sde.types.implant;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHpBonus2;
import fr.guiguilechat.jcelechat.model.sde.attributes.Implantness;
import fr.guiguilechat.jcelechat.model.sde.attributes.IntelligenceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MemoryBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PerceptionBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.VelocityBonus2;
import fr.guiguilechat.jcelechat.model.sde.attributes.WillpowerBonus;
import fr.guiguilechat.jcelechat.model.sde.types.Implant;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class CyberXSpecials
    extends Implant
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double armorhpbonus2;
    /**
     * Whether an item is an implant or not
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int implantness;
    /**
     * +/- bonus to the intelligence of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int intelligencebonus;
    /**
     * +/- bonus to the memory of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int memorybonus;
    /**
     * +/- bonus to the perception of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int perceptionbonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int velocitybonus2;
    /**
     * +/- bonus to the willpower of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int willpowerbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {IntelligenceBonus.INSTANCE, MemoryBonus.INSTANCE, PerceptionBonus.INSTANCE, WillpowerBonus.INSTANCE, RequiredSkill1Level.INSTANCE, TechLevel.INSTANCE, RequiredSkill1 .INSTANCE, Implantness.INSTANCE, ArmorHpBonus2 .INSTANCE, VelocityBonus2 .INSTANCE })));
    public static final CyberXSpecials.MetaGroup METAGROUP = new CyberXSpecials.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1083 :
            {
                return armorhpbonus2;
            }
            case  331 :
            {
                return implantness;
            }
            case  176 :
            {
                return intelligencebonus;
            }
            case  177 :
            {
                return memorybonus;
            }
            case  178 :
            {
                return perceptionbonus;
            }
            case  422 :
            {
                return techlevel;
            }
            case  1084 :
            {
                return velocitybonus2;
            }
            case  179 :
            {
                return willpowerbonus;
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
    public IMetaGroup<CyberXSpecials> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CyberXSpecials>
    {
        public static final String RESOURCE_PATH = "SDE/types/implant/CyberXSpecials.yaml";
        private Map<Integer, CyberXSpecials> cache = (null);

        @Override
        public IMetaCategory<? super CyberXSpecials> category() {
            return Implant.METACAT;
        }

        @Override
        public int getGroupId() {
            return  783;
        }

        @Override
        public String getName() {
            return "CyberXSpecials";
        }

        @Override
        public synchronized Map<Integer, CyberXSpecials> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CyberXSpecials.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, CyberXSpecials> types;
        }
    }
}
