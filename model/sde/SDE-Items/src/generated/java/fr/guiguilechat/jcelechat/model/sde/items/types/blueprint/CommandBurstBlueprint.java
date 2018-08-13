package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class CommandBurstBlueprint
    extends Blueprint
{
    public final static String RESOURCE_PATH = "SDE/items/blueprint/CommandBurstBlueprint.yaml";
    private static Map<String, CommandBurstBlueprint> cache = (null);

    @Override
    public int getGroupId() {
        return  1812;
    }

    @Override
    public Class<?> getGroup() {
        return CommandBurstBlueprint.class;
    }

    public static synchronized Map<String, CommandBurstBlueprint> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CommandBurstBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, CommandBurstBlueprint> items;
    }
}
