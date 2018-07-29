package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class AbyssalEnvironment
    extends Celestial
{
    public final static String RESOURCE_PATH = "SDE/items/celestial/AbyssalEnvironment.yaml";
    private static LinkedHashMap<String, AbyssalEnvironment> cache = (null);

    @Override
    public int getGroupId() {
        return  1983;
    }

    @Override
    public Class<?> getGroup() {
        return AbyssalEnvironment.class;
    }

    public static synchronized LinkedHashMap<String, AbyssalEnvironment> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AbyssalEnvironment.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AbyssalEnvironment> items;
    }
}
