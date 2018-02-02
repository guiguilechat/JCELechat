package fr.guiguilechat.eveonline.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class Money
    extends Material
{
    public final static String RESOURCE_PATH = "SDE/items/material/Money.yaml";
    private static LinkedHashMap<String, Money> cache = (null);

    @Override
    public int getGroupId() {
        return  17;
    }

    @Override
    public Class<?> getGroup() {
        return Money.class;
    }

    public static synchronized LinkedHashMap<String, Money> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Money.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Money> items;
    }
}
