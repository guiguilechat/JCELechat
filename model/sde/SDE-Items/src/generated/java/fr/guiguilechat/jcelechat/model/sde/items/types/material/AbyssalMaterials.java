package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class AbyssalMaterials
    extends Material
{
    public final static AbyssalMaterials.MetaGroup METAGROUP = new AbyssalMaterials.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/material/AbyssalMaterials.yaml";
    private static Map<String, AbyssalMaterials> cache = (null);

    @Override
    public int getGroupId() {
        return  1996;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<AbyssalMaterials> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, AbyssalMaterials> load() {
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<AbyssalMaterials>
    {

        @Override
        public MetaCategory<? super AbyssalMaterials> category() {
            return Material.METACAT;
        }

        @Override
        public String getName() {
            return "AbyssalMaterials";
        }

        @Override
        public Collection<AbyssalMaterials> items() {
            return (load().values());
        }
    }
}
