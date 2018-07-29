package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;

public class CriminalTags
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/CriminalTags.yaml";
    private static LinkedHashMap<String, CriminalTags> cache = (null);

    @Override
    public int getGroupId() {
        return  370;
    }

    @Override
    public Class<?> getGroup() {
        return CriminalTags.class;
    }

    public static synchronized LinkedHashMap<String, CriminalTags> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CriminalTags.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, CriminalTags> items;
    }
}
