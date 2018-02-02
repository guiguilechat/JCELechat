package fr.guiguilechat.eveonline.model.sde.items.types.structure;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Structure;
import org.yaml.snakeyaml.Yaml;

public class AdministrationHub
    extends Structure
{
    public final static String RESOURCE_PATH = "SDE/items/structure/AdministrationHub.yaml";
    private static LinkedHashMap<String, AdministrationHub> cache = (null);

    @Override
    public int getGroupId() {
        return  1409;
    }

    @Override
    public Class<?> getGroup() {
        return AdministrationHub.class;
    }

    public static synchronized LinkedHashMap<String, AdministrationHub> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AdministrationHub.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AdministrationHub> items;
    }
}
