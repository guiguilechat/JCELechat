package fr.guiguilechat.eveonline.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class EmpireInsigniaDrops
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/EmpireInsigniaDrops.yaml";
    private static LinkedHashMap<String, EmpireInsigniaDrops> cache = (null);

    @Override
    public int getGroupId() {
        return  409;
    }

    @Override
    public Class<?> getGroup() {
        return EmpireInsigniaDrops.class;
    }

    public static synchronized LinkedHashMap<String, EmpireInsigniaDrops> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(EmpireInsigniaDrops.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, EmpireInsigniaDrops> items;
    }
}
