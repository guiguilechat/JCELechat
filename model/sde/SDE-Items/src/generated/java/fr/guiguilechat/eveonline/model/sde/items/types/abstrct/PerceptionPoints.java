package fr.guiguilechat.eveonline.model.sde.items.types.abstrct;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Abstrct;
import org.yaml.snakeyaml.Yaml;

public class PerceptionPoints
    extends Abstrct
{
    public final static String RESOURCE_PATH = "SDE/items/abstrct/PerceptionPoints.yaml";
    private static LinkedHashMap<String, PerceptionPoints> cache = (null);

    @Override
    public int getGroupId() {
        return  1121;
    }

    @Override
    public Class<?> getGroup() {
        return PerceptionPoints.class;
    }

    public static LinkedHashMap<String, PerceptionPoints> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PerceptionPoints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, PerceptionPoints> items;
    }
}
