package fr.guiguilechat.eveonline.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class TechnicalDataChips
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/TechnicalDataChips.yaml";
    private static LinkedHashMap<String, TechnicalDataChips> cache = (null);

    @Override
    public int getGroupId() {
        return  1886;
    }

    @Override
    public Class<?> getGroup() {
        return TechnicalDataChips.class;
    }

    public static synchronized LinkedHashMap<String, TechnicalDataChips> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(TechnicalDataChips.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, TechnicalDataChips> items;
    }
}
