package fr.guiguilechat.eveonline.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class CommandBurstBlueprint
    extends Blueprint
{
    public final static String RESOURCE_PATH = "SDE/items/blueprint/CommandBurstBlueprint.yaml";
    private static LinkedHashMap<String, CommandBurstBlueprint> cache = (null);

    @Override
    public int getGroupId() {
        return  1812;
    }

    @Override
    public Class<?> getGroup() {
        return CommandBurstBlueprint.class;
    }

    public static synchronized LinkedHashMap<String, CommandBurstBlueprint> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CommandBurstBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, CommandBurstBlueprint> items;
    }
}
