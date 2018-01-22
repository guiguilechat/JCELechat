package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class GhostSitesGuristasCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/GhostSitesGuristasCruiser.yaml";
    private static LinkedHashMap<String, GhostSitesGuristasCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1259;
    }

    @Override
    public Class<?> getGroup() {
        return GhostSitesGuristasCruiser.class;
    }

    public static LinkedHashMap<String, GhostSitesGuristasCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(GhostSitesGuristasCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, GhostSitesGuristasCruiser> items;
    }
}
