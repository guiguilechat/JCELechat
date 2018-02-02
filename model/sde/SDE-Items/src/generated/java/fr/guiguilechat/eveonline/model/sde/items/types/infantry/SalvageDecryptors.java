package fr.guiguilechat.eveonline.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class SalvageDecryptors
    extends Infantry
{
    public final static String RESOURCE_PATH = "SDE/items/infantry/SalvageDecryptors.yaml";
    private static LinkedHashMap<String, SalvageDecryptors> cache = (null);

    @Override
    public int getGroupId() {
        return  367776;
    }

    @Override
    public Class<?> getGroup() {
        return SalvageDecryptors.class;
    }

    public static synchronized LinkedHashMap<String, SalvageDecryptors> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SalvageDecryptors.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SalvageDecryptors> items;
    }
}
