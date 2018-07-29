package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class IrregularCarrier
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/IrregularCarrier.yaml";
    private static LinkedHashMap<String, IrregularCarrier> cache = (null);

    @Override
    public int getGroupId() {
        return  1726;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularCarrier.class;
    }

    public static synchronized LinkedHashMap<String, IrregularCarrier> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(IrregularCarrier.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, IrregularCarrier> items;
    }
}
