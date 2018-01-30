package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithMinmatarCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithMinmatarCruiser.yaml";
    private static LinkedHashMap<String, HiddenZenithMinmatarCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1799;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithMinmatarCruiser.class;
    }

    public static LinkedHashMap<String, HiddenZenithMinmatarCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithMinmatarCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, HiddenZenithMinmatarCruiser> items;
    }
}