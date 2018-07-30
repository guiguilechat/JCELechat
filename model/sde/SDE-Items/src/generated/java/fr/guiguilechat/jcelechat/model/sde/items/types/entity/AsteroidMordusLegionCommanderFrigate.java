package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidMordusLegionCommanderFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidMordusLegionCommanderFrigate.yaml";
    private static LinkedHashMap<String, AsteroidMordusLegionCommanderFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1285;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidMordusLegionCommanderFrigate.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidMordusLegionCommanderFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidMordusLegionCommanderFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidMordusLegionCommanderFrigate> items;
    }
}
