package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class EntosisCommandNode
    extends Celestial
{
    public final static String RESOURCE_PATH = "SDE/items/celestial/EntosisCommandNode.yaml";
    private static LinkedHashMap<String, EntosisCommandNode> cache = (null);

    @Override
    public int getGroupId() {
        return  1316;
    }

    @Override
    public Class<?> getGroup() {
        return EntosisCommandNode.class;
    }

    public static synchronized LinkedHashMap<String, EntosisCommandNode> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(EntosisCommandNode.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, EntosisCommandNode> items;
    }
}
