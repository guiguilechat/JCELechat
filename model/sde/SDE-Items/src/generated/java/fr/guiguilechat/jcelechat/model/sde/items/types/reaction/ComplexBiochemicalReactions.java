package fr.guiguilechat.jcelechat.model.sde.items.types.reaction;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Reaction;
import org.yaml.snakeyaml.Yaml;

public class ComplexBiochemicalReactions
    extends Reaction
{
    public final static String RESOURCE_PATH = "SDE/items/reaction/ComplexBiochemicalReactions.yaml";
    private static LinkedHashMap<String, ComplexBiochemicalReactions> cache = (null);

    @Override
    public int getGroupId() {
        return  662;
    }

    @Override
    public Class<?> getGroup() {
        return ComplexBiochemicalReactions.class;
    }

    public static synchronized LinkedHashMap<String, ComplexBiochemicalReactions> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ComplexBiochemicalReactions.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, ComplexBiochemicalReactions> items;
    }
}
