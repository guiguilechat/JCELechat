package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionMinmatarRepublicDestroyer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionMinmatarRepublicDestroyer.yaml";
    private static LinkedHashMap<String, MissionMinmatarRepublicDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  684;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMinmatarRepublicDestroyer.class;
    }

    public static synchronized LinkedHashMap<String, MissionMinmatarRepublicDestroyer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionMinmatarRepublicDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionMinmatarRepublicDestroyer> items;
    }
}
