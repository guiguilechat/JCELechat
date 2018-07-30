package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class SurfaceInfrastructure
    extends Infantry
{
    public final static String RESOURCE_PATH = "SDE/items/infantry/SurfaceInfrastructure.yaml";
    private static LinkedHashMap<String, SurfaceInfrastructure> cache = (null);

    @Override
    public int getGroupId() {
        return  364204;
    }

    @Override
    public Class<?> getGroup() {
        return SurfaceInfrastructure.class;
    }

    public static synchronized LinkedHashMap<String, SurfaceInfrastructure> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SurfaceInfrastructure.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SurfaceInfrastructure> items;
    }
}
