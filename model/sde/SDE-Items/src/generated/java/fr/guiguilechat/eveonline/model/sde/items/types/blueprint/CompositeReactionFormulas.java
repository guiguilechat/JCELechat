package fr.guiguilechat.eveonline.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class CompositeReactionFormulas
    extends Blueprint
{
    public final static String RESOURCE_PATH = "SDE/items/blueprint/CompositeReactionFormulas.yaml";
    private static LinkedHashMap<String, CompositeReactionFormulas> cache = (null);

    @Override
    public int getGroupId() {
        return  1888;
    }

    @Override
    public Class<?> getGroup() {
        return CompositeReactionFormulas.class;
    }

    public static LinkedHashMap<String, CompositeReactionFormulas> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CompositeReactionFormulas.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, CompositeReactionFormulas> items;
    }
}
