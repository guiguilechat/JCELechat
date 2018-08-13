package fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.SpecialEditionAssets;
import org.yaml.snakeyaml.Yaml;

public class SpecialEditionCommodities
    extends SpecialEditionAssets
{
    /**
     * Factor to adjust module cpu need by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double CpuMultiplier;
    public final static SpecialEditionCommodities.MetaGroup METAGROUP = new SpecialEditionCommodities.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/specialeditionassets/SpecialEditionCommodities.yaml";
    private static Map<String, SpecialEditionCommodities> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  202 :
            {
                return CpuMultiplier;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1194;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SpecialEditionCommodities> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, SpecialEditionCommodities> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SpecialEditionCommodities.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SpecialEditionCommodities> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SpecialEditionCommodities>
    {

        @Override
        public MetaCategory<? super SpecialEditionCommodities> category() {
            return SpecialEditionAssets.METACAT;
        }

        @Override
        public String getName() {
            return "SpecialEditionCommodities";
        }

        @Override
        public Collection<SpecialEditionCommodities> items() {
            return (load().values());
        }
    }
}
