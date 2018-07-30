package fr.guiguilechat.jcelechat.model.sde.items.types.reaction;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Reaction;
import org.yaml.snakeyaml.Yaml;

public class EnslavementPrograms
    extends Reaction
{
    public final static String RESOURCE_PATH = "SDE/items/reaction/EnslavementPrograms.yaml";
    private static LinkedHashMap<String, EnslavementPrograms> cache = (null);

    @Override
    public int getGroupId() {
        return  882;
    }

    @Override
    public Class<?> getGroup() {
        return EnslavementPrograms.class;
    }

    public static synchronized LinkedHashMap<String, EnslavementPrograms> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(EnslavementPrograms.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, EnslavementPrograms> items;
    }
}
