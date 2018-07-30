package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class GhostSitesMorduSLegion
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/GhostSitesMorduSLegion.yaml";
    private static LinkedHashMap<String, GhostSitesMorduSLegion> cache = (null);

    @Override
    public int getGroupId() {
        return  1288;
    }

    @Override
    public Class<?> getGroup() {
        return GhostSitesMorduSLegion.class;
    }

    public static synchronized LinkedHashMap<String, GhostSitesMorduSLegion> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(GhostSitesMorduSLegion.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, GhostSitesMorduSLegion> items;
    }
}
