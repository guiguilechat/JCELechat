package fr.guiguilechat.eveonline.model.sde.items.types.implant;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Implant;
import org.yaml.snakeyaml.Yaml;

public class CyberDrones
    extends Implant
{
    public final static String RESOURCE_PATH = "SDE/items/implant/CyberDrones.yaml";
    private static LinkedHashMap<String, CyberDrones> cache = (null);

    @Override
    public int getGroupId() {
        return  739;
    }

    @Override
    public Class<?> getGroup() {
        return CyberDrones.class;
    }

    public static synchronized LinkedHashMap<String, CyberDrones> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CyberDrones.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, CyberDrones> items;
    }
}
