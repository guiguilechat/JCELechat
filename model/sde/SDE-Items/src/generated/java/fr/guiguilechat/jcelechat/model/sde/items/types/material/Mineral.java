package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class Mineral
    extends Material
{
    public final static Mineral.MetaGroup METAGROUP = new Mineral.MetaGroup();

    @Override
    public IMetaGroup<Mineral> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Mineral>
    {
        public final static String RESOURCE_PATH = "SDE/items/material/Mineral.yaml";
        private Map<String, Mineral> cache = (null);

        @Override
        public IMetaCategory<? super Mineral> category() {
            return Material.METACAT;
        }

        @Override
        public int getGroupId() {
            return  18;
        }

        @Override
        public String getName() {
            return "Mineral";
        }

        @Override
        public synchronized Map<String, Mineral> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Mineral.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Mineral> items;
        }
    }
}
