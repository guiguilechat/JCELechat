package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MiningExhumer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MiningExhumer.yaml";
    private static LinkedHashMap<String, MiningExhumer> cache = (null);

    @Override
    public int getGroupId() {
        return  1766;
    }

    @Override
    public Class<?> getGroup() {
        return MiningExhumer.class;
    }

    public static synchronized LinkedHashMap<String, MiningExhumer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MiningExhumer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MiningExhumer> items;
    }
}
