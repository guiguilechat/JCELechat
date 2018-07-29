package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class NPCMiningBarge
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/NPCMiningBarge.yaml";
    private static LinkedHashMap<String, NPCMiningBarge> cache = (null);

    @Override
    public int getGroupId() {
        return  1765;
    }

    @Override
    public Class<?> getGroup() {
        return NPCMiningBarge.class;
    }

    public static synchronized LinkedHashMap<String, NPCMiningBarge> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(NPCMiningBarge.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, NPCMiningBarge> items;
    }
}
