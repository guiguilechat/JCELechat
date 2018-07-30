package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingSerpentisFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/RoamingSerpentisFrigate.yaml";
    private static LinkedHashMap<String, RoamingSerpentisFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1722;
    }

    @Override
    public Class<?> getGroup() {
        return RoamingSerpentisFrigate.class;
    }

    public static synchronized LinkedHashMap<String, RoamingSerpentisFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RoamingSerpentisFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, RoamingSerpentisFrigate> items;
    }
}
