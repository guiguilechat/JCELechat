package fr.guiguilechat.jcelechat.model.sde.items.types.abstrct;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Abstrct;

public class Decorations
    extends Abstrct
{
    public final static String RESOURCE_PATH = "SDE/items/abstrct/Decorations.yaml";
    private static LinkedHashMap<String, Decorations> cache = (null);

    @Override
    public int getGroupId() {
        return  937;
    }

    @Override
    public Class<?> getGroup() {
        return Decorations.class;
    }

    public static synchronized LinkedHashMap<String, Decorations> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Decorations.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Decorations> items;
    }
}
