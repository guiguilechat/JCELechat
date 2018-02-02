package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidMordusLegionCommanderCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidMordusLegionCommanderCruiser.yaml";
    private static LinkedHashMap<String, AsteroidMordusLegionCommanderCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1286;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidMordusLegionCommanderCruiser.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidMordusLegionCommanderCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidMordusLegionCommanderCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidMordusLegionCommanderCruiser> items;
    }
}
