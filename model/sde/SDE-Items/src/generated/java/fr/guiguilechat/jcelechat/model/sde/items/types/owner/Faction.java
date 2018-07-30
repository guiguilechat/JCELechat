package fr.guiguilechat.jcelechat.model.sde.items.types.owner;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Owner;
import org.yaml.snakeyaml.Yaml;

public class Faction
    extends Owner
{
    public final static String RESOURCE_PATH = "SDE/items/owner/Faction.yaml";
    private static LinkedHashMap<String, Faction> cache = (null);

    @Override
    public int getGroupId() {
        return  19;
    }

    @Override
    public Class<?> getGroup() {
        return Faction.class;
    }

    public static synchronized LinkedHashMap<String, Faction> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Faction.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Faction> items;
    }
}
