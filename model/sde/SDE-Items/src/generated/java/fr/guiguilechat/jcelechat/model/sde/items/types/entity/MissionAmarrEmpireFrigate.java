package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionAmarrEmpireFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionAmarrEmpireFrigate.yaml";
    private static LinkedHashMap<String, MissionAmarrEmpireFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  665;
    }

    @Override
    public Class<?> getGroup() {
        return MissionAmarrEmpireFrigate.class;
    }

    public static synchronized LinkedHashMap<String, MissionAmarrEmpireFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionAmarrEmpireFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionAmarrEmpireFrigate> items;
    }
}
