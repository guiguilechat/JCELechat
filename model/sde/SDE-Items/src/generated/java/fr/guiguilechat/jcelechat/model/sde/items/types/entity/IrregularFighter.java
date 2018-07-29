package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class IrregularFighter
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/IrregularFighter.yaml";
    private static LinkedHashMap<String, IrregularFighter> cache = (null);

    @Override
    public int getGroupId() {
        return  1454;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularFighter.class;
    }

    public static synchronized LinkedHashMap<String, IrregularFighter> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(IrregularFighter.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, IrregularFighter> items;
    }
}
