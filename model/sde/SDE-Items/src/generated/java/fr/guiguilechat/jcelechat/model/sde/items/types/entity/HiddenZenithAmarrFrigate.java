package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithAmarrFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithAmarrFrigate.yaml";
    private static LinkedHashMap<String, HiddenZenithAmarrFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1791;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithAmarrFrigate.class;
    }

    public static synchronized LinkedHashMap<String, HiddenZenithAmarrFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithAmarrFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, HiddenZenithAmarrFrigate> items;
    }
}
