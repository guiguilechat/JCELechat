package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class CustomsOfficial
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/CustomsOfficial.yaml";
    private static LinkedHashMap<String, CustomsOfficial> cache = (null);

    @Override
    public int getGroupId() {
        return  446;
    }

    @Override
    public Class<?> getGroup() {
        return CustomsOfficial.class;
    }

    public static LinkedHashMap<String, CustomsOfficial> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CustomsOfficial.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, CustomsOfficial> items;
    }
}
