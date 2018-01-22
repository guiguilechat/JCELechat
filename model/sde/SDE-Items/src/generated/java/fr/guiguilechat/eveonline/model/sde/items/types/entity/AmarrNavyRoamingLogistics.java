package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AmarrNavyRoamingLogistics
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AmarrNavyRoamingLogistics.yaml";
    private static LinkedHashMap<String, AmarrNavyRoamingLogistics> cache = (null);

    @Override
    public int getGroupId() {
        return  1413;
    }

    @Override
    public Class<?> getGroup() {
        return AmarrNavyRoamingLogistics.class;
    }

    public static LinkedHashMap<String, AmarrNavyRoamingLogistics> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AmarrNavyRoamingLogistics.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AmarrNavyRoamingLogistics> items;
    }
}
