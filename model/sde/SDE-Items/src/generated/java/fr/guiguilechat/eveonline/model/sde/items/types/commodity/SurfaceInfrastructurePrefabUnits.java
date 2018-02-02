package fr.guiguilechat.eveonline.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class SurfaceInfrastructurePrefabUnits
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/SurfaceInfrastructurePrefabUnits.yaml";
    private static LinkedHashMap<String, SurfaceInfrastructurePrefabUnits> cache = (null);

    @Override
    public int getGroupId() {
        return  1118;
    }

    @Override
    public Class<?> getGroup() {
        return SurfaceInfrastructurePrefabUnits.class;
    }

    public static synchronized LinkedHashMap<String, SurfaceInfrastructurePrefabUnits> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SurfaceInfrastructurePrefabUnits.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SurfaceInfrastructurePrefabUnits> items;
    }
}
