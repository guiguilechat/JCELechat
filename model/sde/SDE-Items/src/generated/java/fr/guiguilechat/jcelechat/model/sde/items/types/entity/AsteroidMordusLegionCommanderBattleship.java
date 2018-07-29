package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class AsteroidMordusLegionCommanderBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidMordusLegionCommanderBattleship.yaml";
    private static LinkedHashMap<String, AsteroidMordusLegionCommanderBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  1287;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidMordusLegionCommanderBattleship.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidMordusLegionCommanderBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidMordusLegionCommanderBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidMordusLegionCommanderBattleship> items;
    }
}
