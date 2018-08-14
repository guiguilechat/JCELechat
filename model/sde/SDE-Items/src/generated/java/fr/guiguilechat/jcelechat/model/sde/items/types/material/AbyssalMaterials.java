package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class AbyssalMaterials
    extends Material
{
    public final static AbyssalMaterials.MetaGroup METAGROUP = new AbyssalMaterials.MetaGroup();

    @Override
    public IMetaGroup<AbyssalMaterials> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AbyssalMaterials>
    {
        public final static String RESOURCE_PATH = "SDE/items/material/AbyssalMaterials.yaml";
        private Map<String, AbyssalMaterials> cache = (null);

        @Override
        public IMetaCategory<? super AbyssalMaterials> category() {
            return Material.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1996;
        }

        @Override
        public String getName() {
            return "AbyssalMaterials";
        }

        @Override
        public synchronized Map<String, AbyssalMaterials> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AbyssalMaterials.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AbyssalMaterials> items;
        }
    }
}
