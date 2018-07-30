package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class InfantryInstallations
    extends Infantry
{
    public final static String RESOURCE_PATH = "SDE/items/infantry/InfantryInstallations.yaml";
    private static LinkedHashMap<String, InfantryInstallations> cache = (null);

    @Override
    public int getGroupId() {
        return  354753;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryInstallations.class;
    }

    public static synchronized LinkedHashMap<String, InfantryInstallations> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(InfantryInstallations.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, InfantryInstallations> items;
    }
}
