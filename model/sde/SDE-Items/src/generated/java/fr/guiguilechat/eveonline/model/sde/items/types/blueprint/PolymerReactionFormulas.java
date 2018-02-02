package fr.guiguilechat.eveonline.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class PolymerReactionFormulas
    extends Blueprint
{
    public final static String RESOURCE_PATH = "SDE/items/blueprint/PolymerReactionFormulas.yaml";
    private static LinkedHashMap<String, PolymerReactionFormulas> cache = (null);

    @Override
    public int getGroupId() {
        return  1889;
    }

    @Override
    public Class<?> getGroup() {
        return PolymerReactionFormulas.class;
    }

    public static synchronized LinkedHashMap<String, PolymerReactionFormulas> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PolymerReactionFormulas.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, PolymerReactionFormulas> items;
    }
}
