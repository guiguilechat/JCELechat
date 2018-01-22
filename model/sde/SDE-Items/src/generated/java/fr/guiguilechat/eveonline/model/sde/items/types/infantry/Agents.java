package fr.guiguilechat.eveonline.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class Agents
    extends Infantry
{
    public final static String RESOURCE_PATH = "SDE/items/infantry/Agents.yaml";
    private static LinkedHashMap<String, Agents> cache = (null);

    @Override
    public int getGroupId() {
        return  367580;
    }

    @Override
    public Class<?> getGroup() {
        return Agents.class;
    }

    public static LinkedHashMap<String, Agents> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Agents.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Agents> items;
    }
}
