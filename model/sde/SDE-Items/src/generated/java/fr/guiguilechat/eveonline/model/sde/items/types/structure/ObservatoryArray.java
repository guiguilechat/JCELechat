package fr.guiguilechat.eveonline.model.sde.items.types.structure;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Structure;
import org.yaml.snakeyaml.Yaml;

public class ObservatoryArray
    extends Structure
{
    public final static String RESOURCE_PATH = "SDE/items/structure/ObservatoryArray.yaml";
    private static LinkedHashMap<String, ObservatoryArray> cache = (null);

    @Override
    public int getGroupId() {
        return  1407;
    }

    @Override
    public Class<?> getGroup() {
        return ObservatoryArray.class;
    }

    public static synchronized LinkedHashMap<String, ObservatoryArray> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ObservatoryArray.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, ObservatoryArray> items;
    }
}
