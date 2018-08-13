package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/IrregularBattleship.yaml";
    private static Map<String, IrregularBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  1667;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularBattleship.class;
    }

    public static synchronized Map<String, IrregularBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(IrregularBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, IrregularBattleship> items;
    }
}
