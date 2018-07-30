package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularIndustrial
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/IrregularIndustrial.yaml";
    private static LinkedHashMap<String, IrregularIndustrial> cache = (null);

    @Override
    public int getGroupId() {
        return  1895;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularIndustrial.class;
    }

    public static synchronized LinkedHashMap<String, IrregularIndustrial> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(IrregularIndustrial.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, IrregularIndustrial> items;
    }
}
