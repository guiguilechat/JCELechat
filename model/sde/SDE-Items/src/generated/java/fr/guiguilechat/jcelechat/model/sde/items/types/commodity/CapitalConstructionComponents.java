package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class CapitalConstructionComponents
    extends Commodity
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int MoonMiningAmount;
    public final static CapitalConstructionComponents.MetaGroup METAGROUP = new CapitalConstructionComponents.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  726 :
            {
                return MoonMiningAmount;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<CapitalConstructionComponents> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CapitalConstructionComponents>
    {
        public final static String RESOURCE_PATH = "SDE/items/commodity/CapitalConstructionComponents.yaml";
        private Map<String, CapitalConstructionComponents> cache = (null);

        @Override
        public IMetaCategory<? super CapitalConstructionComponents> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  873;
        }

        @Override
        public String getName() {
            return "CapitalConstructionComponents";
        }

        @Override
        public synchronized Map<String, CapitalConstructionComponents> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(CapitalConstructionComponents.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CapitalConstructionComponents> items;
        }
    }
}
