package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionGallenteFederationBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionGallenteFederationBattleship.yaml";
    private static LinkedHashMap<String, MissionGallenteFederationBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  680;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGallenteFederationBattleship.class;
    }

    public static synchronized LinkedHashMap<String, MissionGallenteFederationBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionGallenteFederationBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionGallenteFederationBattleship> items;
    }
}
