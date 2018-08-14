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

public class StationComponents
    extends Commodity
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int MoonMiningAmount;
    public final static StationComponents.MetaGroup METAGROUP = new StationComponents.MetaGroup();

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
    public IMetaGroup<StationComponents> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StationComponents>
    {
        public final static String RESOURCE_PATH = "SDE/items/commodity/StationComponents.yaml";
        private Map<String, StationComponents> cache = (null);

        @Override
        public IMetaCategory<? super StationComponents> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  536;
        }

        @Override
        public String getName() {
            return "StationComponents";
        }

        @Override
        public synchronized Map<String, StationComponents> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(StationComponents.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StationComponents> items;
        }
    }
}
