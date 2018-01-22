package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingSerpentisBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/RoamingSerpentisBattleship.yaml";
    private static LinkedHashMap<String, RoamingSerpentisBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  1720;
    }

    @Override
    public Class<?> getGroup() {
        return RoamingSerpentisBattleship.class;
    }

    public static LinkedHashMap<String, RoamingSerpentisBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RoamingSerpentisBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, RoamingSerpentisBattleship> items;
    }
}
