package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class RogueDroneComponents
    extends Material
{
    public final static RogueDroneComponents.MetaGroup METAGROUP = new RogueDroneComponents.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/material/RogueDroneComponents.yaml";
    private static Map<String, RogueDroneComponents> cache = (null);

    @Override
    public int getGroupId() {
        return  886;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<RogueDroneComponents> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, RogueDroneComponents> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RogueDroneComponents.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, RogueDroneComponents> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<RogueDroneComponents>
    {

        @Override
        public MetaCategory<? super RogueDroneComponents> category() {
            return Material.METACAT;
        }

        @Override
        public String getName() {
            return "RogueDroneComponents";
        }

        @Override
        public Collection<RogueDroneComponents> items() {
            return (load().values());
        }
    }
}
