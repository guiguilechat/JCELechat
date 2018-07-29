package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class SentryGun
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/SentryGun.yaml";
    private static LinkedHashMap<String, SentryGun> cache = (null);

    @Override
    public int getGroupId() {
        return  99;
    }

    @Override
    public Class<?> getGroup() {
        return SentryGun.class;
    }

    public static synchronized LinkedHashMap<String, SentryGun> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SentryGun.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SentryGun> items;
    }
}
