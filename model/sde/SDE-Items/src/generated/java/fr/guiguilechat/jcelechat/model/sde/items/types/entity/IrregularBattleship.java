package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class IrregularBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/IrregularBattleship.yaml";
    private static LinkedHashMap<String, IrregularBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  1667;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularBattleship.class;
    }

    public static synchronized LinkedHashMap<String, IrregularBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(IrregularBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, IrregularBattleship> items;
    }
}
