package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularDestroyer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/IrregularDestroyer.yaml";
    private static LinkedHashMap<String, IrregularDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  1664;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularDestroyer.class;
    }

    public static synchronized LinkedHashMap<String, IrregularDestroyer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(IrregularDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, IrregularDestroyer> items;
    }
}
