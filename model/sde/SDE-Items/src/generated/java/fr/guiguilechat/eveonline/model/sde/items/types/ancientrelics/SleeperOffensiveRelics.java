package fr.guiguilechat.eveonline.model.sde.items.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.AncientRelics;
import org.yaml.snakeyaml.Yaml;

public class SleeperOffensiveRelics
    extends AncientRelics
{
    public final static String RESOURCE_PATH = "SDE/items/ancientrelics/SleeperOffensiveRelics.yaml";
    private static LinkedHashMap<String, SleeperOffensiveRelics> cache = (null);

    @Override
    public int getGroupId() {
        return  991;
    }

    @Override
    public Class<?> getGroup() {
        return SleeperOffensiveRelics.class;
    }

    public static synchronized LinkedHashMap<String, SleeperOffensiveRelics> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SleeperOffensiveRelics.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SleeperOffensiveRelics> items;
    }
}
