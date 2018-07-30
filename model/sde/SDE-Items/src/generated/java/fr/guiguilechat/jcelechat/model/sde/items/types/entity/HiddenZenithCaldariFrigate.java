package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithCaldariFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithCaldariFrigate.yaml";
    private static LinkedHashMap<String, HiddenZenithCaldariFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1794;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithCaldariFrigate.class;
    }

    public static synchronized LinkedHashMap<String, HiddenZenithCaldariFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithCaldariFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, HiddenZenithCaldariFrigate> items;
    }
}
