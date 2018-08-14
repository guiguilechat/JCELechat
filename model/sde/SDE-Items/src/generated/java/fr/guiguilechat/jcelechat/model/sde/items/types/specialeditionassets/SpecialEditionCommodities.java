package fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
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
    public IMetaGroup<SpecialEditionCommodities> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SpecialEditionCommodities>
    {
        public final static String RESOURCE_PATH = "SDE/items/specialeditionassets/SpecialEditionCommodities.yaml";
        private Map<String, SpecialEditionCommodities> cache = (null);

        @Override
        public IMetaCategory<? super SpecialEditionCommodities> category() {
            return SpecialEditionAssets.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1194;
        }

        @Override
        public String getName() {
            return "SpecialEditionCommodities";
        }

        @Override
        public synchronized Map<String, SpecialEditionCommodities> load() {
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
    }
}
