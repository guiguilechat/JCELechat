package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class HiddenZenithCaldariBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithCaldariBattleship.yaml";
    private static LinkedHashMap<String, HiddenZenithCaldariBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  1792;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithCaldariBattleship.class;
    }

    public static synchronized LinkedHashMap<String, HiddenZenithCaldariBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithCaldariBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, HiddenZenithCaldariBattleship> items;
    }
}
