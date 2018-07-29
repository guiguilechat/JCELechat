package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class AsteroidBloodRaidersOfficer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersOfficer.yaml";
    private static LinkedHashMap<String, AsteroidBloodRaidersOfficer> cache = (null);

    @Override
    public int getGroupId() {
        return  559;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidBloodRaidersOfficer.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidBloodRaidersOfficer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersOfficer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidBloodRaidersOfficer> items;
    }
}
