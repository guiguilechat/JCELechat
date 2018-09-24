package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithDrifters
    extends Entity
{
    public final static HiddenZenithDrifters.MetaGroup METAGROUP = new HiddenZenithDrifters.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithDrifters> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithDrifters>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithDrifters.yaml";
        private Map<String, HiddenZenithDrifters> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithDrifters> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1788;
        }

        @Override
        public String getName() {
            return "HiddenZenithDrifters";
        }

        @Override
        public synchronized Map<String, HiddenZenithDrifters> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithDrifters.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithDrifters> items;
        }
    }
}
