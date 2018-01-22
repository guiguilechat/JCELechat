package fr.guiguilechat.eveonline.model.sde.items.types.reaction;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Reaction;
import org.yaml.snakeyaml.Yaml;

public class ComplexReactions
    extends Reaction
{
    public final static String RESOURCE_PATH = "SDE/items/reaction/ComplexReactions.yaml";
    private static LinkedHashMap<String, ComplexReactions> cache = (null);

    @Override
    public int getGroupId() {
        return  484;
    }

    @Override
    public Class<?> getGroup() {
        return ComplexReactions.class;
    }

    public static LinkedHashMap<String, ComplexReactions> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ComplexReactions.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, ComplexReactions> items;
    }
}
