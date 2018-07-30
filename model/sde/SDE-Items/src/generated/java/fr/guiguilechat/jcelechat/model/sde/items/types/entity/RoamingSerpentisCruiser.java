package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingSerpentisCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/RoamingSerpentisCruiser.yaml";
    private static LinkedHashMap<String, RoamingSerpentisCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1721;
    }

    @Override
    public Class<?> getGroup() {
        return RoamingSerpentisCruiser.class;
    }

    public static synchronized LinkedHashMap<String, RoamingSerpentisCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RoamingSerpentisCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, RoamingSerpentisCruiser> items;
    }
}
