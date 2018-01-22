package fr.guiguilechat.eveonline.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class SubsystemBlueprints
    extends Blueprint
{
    public final static String RESOURCE_PATH = "SDE/items/blueprint/SubsystemBlueprints.yaml";
    private static LinkedHashMap<String, SubsystemBlueprints> cache = (null);

    @Override
    public int getGroupId() {
        return  973;
    }

    @Override
    public Class<?> getGroup() {
        return SubsystemBlueprints.class;
    }

    public static LinkedHashMap<String, SubsystemBlueprints> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SubsystemBlueprints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SubsystemBlueprints> items;
    }
}
