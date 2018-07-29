package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceSanshaSNationDestroyer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSanshaSNationDestroyer.yaml";
    private static LinkedHashMap<String, DeadspaceSanshaSNationDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  623;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSanshaSNationDestroyer.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceSanshaSNationDestroyer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSanshaSNationDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceSanshaSNationDestroyer> items;
    }
}
