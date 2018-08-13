package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class ConstructionComponents
    extends Commodity
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int MoonMiningAmount;
    public final static ConstructionComponents.MetaGroup METAGROUP = new ConstructionComponents.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/commodity/ConstructionComponents.yaml";
    private static Map<String, ConstructionComponents> cache = (null);

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
    public int getGroupId() {
        return  334;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ConstructionComponents> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, ConstructionComponents> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ConstructionComponents.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, ConstructionComponents> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ConstructionComponents>
    {

        @Override
        public MetaCategory<? super ConstructionComponents> category() {
            return Commodity.METACAT;
        }

        @Override
        public String getName() {
            return "ConstructionComponents";
        }

        @Override
        public Collection<ConstructionComponents> items() {
            return (load().values());
        }
    }
}
