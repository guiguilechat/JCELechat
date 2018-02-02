package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class StructureEntities
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/StructureEntities.yaml";
    private static LinkedHashMap<String, StructureEntities> cache = (null);

    @Override
    public int getGroupId() {
        return  1872;
    }

    @Override
    public Class<?> getGroup() {
        return StructureEntities.class;
    }

    public static synchronized LinkedHashMap<String, StructureEntities> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StructureEntities.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, StructureEntities> items;
    }
}
