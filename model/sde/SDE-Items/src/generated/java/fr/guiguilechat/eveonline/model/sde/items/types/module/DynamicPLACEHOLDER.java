package fr.guiguilechat.eveonline.model.sde.items.types.module;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class DynamicPLACEHOLDER
    extends Module
{
    public final static String RESOURCE_PATH = "SDE/items/module/DynamicPLACEHOLDER.yaml";
    private static LinkedHashMap<String, DynamicPLACEHOLDER> cache = (null);

    @Override
    public int getGroupId() {
        return  1969;
    }

    @Override
    public Class<?> getGroup() {
        return DynamicPLACEHOLDER.class;
    }

    public static synchronized LinkedHashMap<String, DynamicPLACEHOLDER> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DynamicPLACEHOLDER.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DynamicPLACEHOLDER> items;
    }
}
