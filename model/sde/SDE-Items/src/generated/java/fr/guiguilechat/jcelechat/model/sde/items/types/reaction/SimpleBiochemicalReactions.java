package fr.guiguilechat.jcelechat.model.sde.items.types.reaction;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Reaction;
import org.yaml.snakeyaml.Yaml;

public class SimpleBiochemicalReactions
    extends Reaction
{
    public final static String RESOURCE_PATH = "SDE/items/reaction/SimpleBiochemicalReactions.yaml";
    private static LinkedHashMap<String, SimpleBiochemicalReactions> cache = (null);

    @Override
    public int getGroupId() {
        return  661;
    }

    @Override
    public Class<?> getGroup() {
        return SimpleBiochemicalReactions.class;
    }

    public static synchronized LinkedHashMap<String, SimpleBiochemicalReactions> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SimpleBiochemicalReactions.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SimpleBiochemicalReactions> items;
    }
}
