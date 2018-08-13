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

public class CyberTargeting
    extends Implant
{
    /**
     * Whether an item is an implant or not
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Implantness;
    /**
     * Bonus to Max Targeting Range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double MaxTargetRangeBonus;
    /**
     * Bonus for scan resolution
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanResolutionBonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    public final static CyberTargeting.MetaGroup METAGROUP = new CyberTargeting.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/implant/CyberTargeting.yaml";
    private static Map<String, CyberTargeting> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  331 :
            {
                return Implantness;
            }
            case  309 :
            {
                return MaxTargetRangeBonus;
            }
            case  566 :
            {
                return ScanResolutionBonus;
            }
            case  422 :
            {
                return TechLevel;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1228;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<CyberTargeting> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, CyberTargeting> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CyberTargeting.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, CyberTargeting> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<CyberTargeting>
    {

        @Override
        public MetaCategory<? super CyberTargeting> category() {
            return Implant.METACAT;
        }

        @Override
        public String getName() {
            return "CyberTargeting";
        }

        @Override
        public Collection<CyberTargeting> items() {
            return (load().values());
        }
    }
}
