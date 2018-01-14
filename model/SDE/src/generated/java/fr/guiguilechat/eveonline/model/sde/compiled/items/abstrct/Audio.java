
package fr.guiguilechat.eveonline.model.sde.compiled.items.abstrct;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Abstrct;
import org.yaml.snakeyaml.Yaml;

public class Audio
    extends Abstrct
{

    public final static String RESOURCE_PATH = "SDE/abstrct/Audio.yaml";
    private static LinkedHashMap<String, Audio> cache = (null);

    @Override
    public int getGroupId() {
        return  1109;
    }

    @Override
    public Class<?> getGroup() {
        return Audio.class;
    }

    public static LinkedHashMap<String, Audio> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Audio> items;

    }

}
