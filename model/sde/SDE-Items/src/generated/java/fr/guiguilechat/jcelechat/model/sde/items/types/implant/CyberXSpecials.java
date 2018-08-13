package fr.guiguilechat.jcelechat.model.sde.items.types.implant;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Implant;
import org.yaml.snakeyaml.Yaml;

public class CyberXSpecials
    extends Implant
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ArmorHpBonus2;
    /**
     * Whether an item is an implant or not
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Implantness;
    /**
     * +/- bonus to the intelligence of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IntelligenceBonus;
    /**
     * +/- bonus to the memory of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MemoryBonus;
    /**
     * +/- bonus to the perception of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PerceptionBonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int VelocityBonus2;
    /**
     * +/- bonus to the willpower of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int WillpowerBonus;
    public final static CyberXSpecials.MetaGroup METAGROUP = new CyberXSpecials.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/implant/CyberXSpecials.yaml";
    private static Map<String, CyberXSpecials> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1083 :
            {
                return ArmorHpBonus2;
            }
            case  331 :
            {
                return Implantness;
            }
            case  176 :
            {
                return IntelligenceBonus;
            }
            case  177 :
            {
                return MemoryBonus;
            }
            case  178 :
            {
                return PerceptionBonus;
            }
            case  422 :
            {
                return TechLevel;
            }
            case  1084 :
            {
                return VelocityBonus2;
            }
            case  179 :
            {
                return WillpowerBonus;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  783;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<CyberXSpecials> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, CyberXSpecials> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CyberXSpecials.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, CyberXSpecials> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<CyberXSpecials>
    {

        @Override
        public MetaCategory<? super CyberXSpecials> category() {
            return Implant.METACAT;
        }

        @Override
        public String getName() {
            return "CyberXSpecials";
        }

        @Override
        public Collection<CyberXSpecials> items() {
            return (load().values());
        }
    }
}
