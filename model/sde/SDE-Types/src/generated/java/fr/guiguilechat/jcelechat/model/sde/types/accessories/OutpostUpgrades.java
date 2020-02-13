package fr.guiguilechat.jcelechat.model.sde.types.accessories;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Accessories;
import org.yaml.snakeyaml.Yaml;

public class OutpostUpgrades
    extends Accessories
{
    public static final OutpostUpgrades.MetaGroup METAGROUP = new OutpostUpgrades.MetaGroup();

    @Override
    public IMetaGroup<OutpostUpgrades> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<OutpostUpgrades>
    {
        public static final String RESOURCE_PATH = "SDE/items/accessories/OutpostUpgrades.yaml";
        private Map<String, OutpostUpgrades> cache = (null);

        @Override
        public IMetaCategory<? super OutpostUpgrades> category() {
            return Accessories.METACAT;
        }

        @Override
        public int getGroupId() {
            return  876;
        }

        @Override
        public String getName() {
            return "OutpostUpgrades";
        }

        @Override
        public synchronized Map<String, OutpostUpgrades> load() {
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
    }
}
