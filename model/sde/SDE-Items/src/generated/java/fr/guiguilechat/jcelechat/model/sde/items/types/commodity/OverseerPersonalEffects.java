package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class OverseerPersonalEffects
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/OverseerPersonalEffects.yaml";
    private static LinkedHashMap<String, OverseerPersonalEffects> cache = (null);

    @Override
    public int getGroupId() {
        return  493;
    }

    @Override
    public Class<?> getGroup() {
        return OverseerPersonalEffects.class;
    }

    public static synchronized LinkedHashMap<String, OverseerPersonalEffects> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(OverseerPersonalEffects.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, OverseerPersonalEffects> items;
    }
}
