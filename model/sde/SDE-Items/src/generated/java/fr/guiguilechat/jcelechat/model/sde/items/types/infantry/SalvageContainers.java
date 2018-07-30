package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class SalvageContainers
    extends Infantry
{
    public final static String RESOURCE_PATH = "SDE/items/infantry/SalvageContainers.yaml";
    private static LinkedHashMap<String, SalvageContainers> cache = (null);

    @Override
    public int getGroupId() {
        return  367774;
    }

    @Override
    public Class<?> getGroup() {
        return SalvageContainers.class;
    }

    public static synchronized LinkedHashMap<String, SalvageContainers> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SalvageContainers.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SalvageContainers> items;
    }
}
