package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class ForceAuxiliary
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/ForceAuxiliary.yaml";
    private static LinkedHashMap<String, ForceAuxiliary> cache = (null);

    @Override
    public int getGroupId() {
        return  1879;
    }

    @Override
    public Class<?> getGroup() {
        return ForceAuxiliary.class;
    }

    public static synchronized LinkedHashMap<String, ForceAuxiliary> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ForceAuxiliary.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, ForceAuxiliary> items;
    }
}
