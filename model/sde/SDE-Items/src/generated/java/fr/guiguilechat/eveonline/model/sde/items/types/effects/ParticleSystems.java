
package fr.guiguilechat.eveonline.model.sde.items.types.effects;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Effects;
import org.yaml.snakeyaml.Yaml;

public class ParticleSystems
    extends Effects
{

    public final static String RESOURCE_PATH = "SDE/items/effects/ParticleSystems.yaml";
    private static LinkedHashMap<String, ParticleSystems> cache = (null);

    @Override
    public int getGroupId() {
        return  1107;
    }

    @Override
    public Class<?> getGroup() {
        return ParticleSystems.class;
    }

    public static LinkedHashMap<String, ParticleSystems> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ParticleSystems.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, ParticleSystems> items;

    }

}
