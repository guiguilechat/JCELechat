package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AmarrNavyRoamingBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AmarrNavyRoamingBattleship.yaml";
    private static LinkedHashMap<String, AmarrNavyRoamingBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  1402;
    }

    @Override
    public Class<?> getGroup() {
        return AmarrNavyRoamingBattleship.class;
    }

    public static synchronized LinkedHashMap<String, AmarrNavyRoamingBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AmarrNavyRoamingBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AmarrNavyRoamingBattleship> items;
    }
}
