package fr.guiguilechat.jcelechat.model.sde.items.types.accessories;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Accessories;
import org.yaml.snakeyaml.Yaml;

public class OutpostUpgrades
    extends Accessories
{
    public final static OutpostUpgrades.MetaGroup METAGROUP = new OutpostUpgrades.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/accessories/OutpostUpgrades.yaml";
    private static Map<String, OutpostUpgrades> cache = (null);

    @Override
    public int getGroupId() {
        return  876;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<OutpostUpgrades> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, OutpostUpgrades> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(OutpostUpgrades.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, OutpostUpgrades> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<OutpostUpgrades>
    {

        @Override
        public MetaCategory<? super OutpostUpgrades> category() {
            return Accessories.METACAT;
        }

        @Override
        public String getName() {
            return "OutpostUpgrades";
        }

        @Override
        public Collection<OutpostUpgrades> items() {
            return (load().values());
        }
    }
}
