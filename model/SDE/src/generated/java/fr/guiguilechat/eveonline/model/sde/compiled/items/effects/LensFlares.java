
package fr.guiguilechat.eveonline.model.sde.compiled.items.effects;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Effects;
import org.yaml.snakeyaml.Yaml;

public class LensFlares
    extends Effects
{

    public final static String RESOURCE_PATH = "SDE/effects/LensFlares.yaml";
    private static LinkedHashMap<String, LensFlares> cache = (null);

    @Override
    public int getGroupId() {
        return  1105;
    }

    @Override
    public Class<?> getGroup() {
        return LensFlares.class;
    }

    public static LinkedHashMap<String, LensFlares> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, LensFlares> items;

    }

}
