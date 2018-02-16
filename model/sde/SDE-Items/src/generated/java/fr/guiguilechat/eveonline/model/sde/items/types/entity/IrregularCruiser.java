package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/IrregularCruiser.yaml";
    private static LinkedHashMap<String, IrregularCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1665;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularCruiser.class;
    }

    public static synchronized LinkedHashMap<String, IrregularCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(IrregularCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, IrregularCruiser> items;
    }
}
