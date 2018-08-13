package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularDreadnought
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/IrregularDreadnought.yaml";
    private static Map<String, IrregularDreadnought> cache = (null);

    @Override
    public int getGroupId() {
        return  1724;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularDreadnought.class;
    }

    public static synchronized Map<String, IrregularDreadnought> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(IrregularDreadnought.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, IrregularDreadnought> items;
    }
}
