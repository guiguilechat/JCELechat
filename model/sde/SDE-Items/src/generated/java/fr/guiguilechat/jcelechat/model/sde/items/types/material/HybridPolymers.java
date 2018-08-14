package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class HybridPolymers
    extends Material
{
    public final static HybridPolymers.MetaGroup METAGROUP = new HybridPolymers.MetaGroup();

    @Override
    public IMetaGroup<HybridPolymers> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HybridPolymers>
    {
        public final static String RESOURCE_PATH = "SDE/items/material/HybridPolymers.yaml";
        private Map<String, HybridPolymers> cache = (null);

        @Override
        public IMetaCategory<? super HybridPolymers> category() {
            return Material.METACAT;
        }

        @Override
        public int getGroupId() {
            return  974;
        }

        @Override
        public String getName() {
            return "HybridPolymers";
        }

        @Override
        public synchronized Map<String, HybridPolymers> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HybridPolymers.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HybridPolymers> items;
        }
    }
}
