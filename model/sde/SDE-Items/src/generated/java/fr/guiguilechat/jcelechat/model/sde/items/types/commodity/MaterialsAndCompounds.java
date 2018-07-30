package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class MaterialsAndCompounds
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/MaterialsAndCompounds.yaml";
    private static LinkedHashMap<String, MaterialsAndCompounds> cache = (null);

    @Override
    public int getGroupId() {
        return  530;
    }

    @Override
    public Class<?> getGroup() {
        return MaterialsAndCompounds.class;
    }

    public static synchronized LinkedHashMap<String, MaterialsAndCompounds> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MaterialsAndCompounds.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MaterialsAndCompounds> items;
    }
}
