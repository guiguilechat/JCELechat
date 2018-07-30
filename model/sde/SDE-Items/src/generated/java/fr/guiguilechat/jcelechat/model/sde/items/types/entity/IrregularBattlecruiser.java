package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularBattlecruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/IrregularBattlecruiser.yaml";
    private static LinkedHashMap<String, IrregularBattlecruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1666;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularBattlecruiser.class;
    }

    public static synchronized LinkedHashMap<String, IrregularBattlecruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(IrregularBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, IrregularBattlecruiser> items;
    }
}
