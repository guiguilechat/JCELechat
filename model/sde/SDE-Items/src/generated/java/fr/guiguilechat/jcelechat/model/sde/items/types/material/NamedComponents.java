package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class NamedComponents
    extends Material
{
    public final static NamedComponents.MetaGroup METAGROUP = new NamedComponents.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/material/NamedComponents.yaml";
    private static Map<String, NamedComponents> cache = (null);

    @Override
    public int getGroupId() {
        return  1676;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<NamedComponents> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, NamedComponents> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(NamedComponents.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, NamedComponents> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<NamedComponents>
    {

        @Override
        public MetaCategory<? super NamedComponents> category() {
            return Material.METACAT;
        }

        @Override
        public String getName() {
            return "NamedComponents";
        }

        @Override
        public Collection<NamedComponents> items() {
            return (load().values());
        }
    }
}
