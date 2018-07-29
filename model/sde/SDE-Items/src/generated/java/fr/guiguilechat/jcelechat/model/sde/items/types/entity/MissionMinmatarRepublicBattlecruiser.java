package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionMinmatarRepublicBattlecruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionMinmatarRepublicBattlecruiser.yaml";
    private static LinkedHashMap<String, MissionMinmatarRepublicBattlecruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  685;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMinmatarRepublicBattlecruiser.class;
    }

    public static synchronized LinkedHashMap<String, MissionMinmatarRepublicBattlecruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionMinmatarRepublicBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionMinmatarRepublicBattlecruiser> items;
    }
}
