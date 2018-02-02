package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGallenteFederationDestroyer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionGallenteFederationDestroyer.yaml";
    private static LinkedHashMap<String, MissionGallenteFederationDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  679;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGallenteFederationDestroyer.class;
    }

    public static synchronized LinkedHashMap<String, MissionGallenteFederationDestroyer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionGallenteFederationDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionGallenteFederationDestroyer> items;
    }
}
