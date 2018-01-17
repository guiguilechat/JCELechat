
package fr.guiguilechat.eveonline.model.sde.compiled.items.abstrct;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Abstrct;
import org.yaml.snakeyaml.Yaml;

public class Decorations
    extends Abstrct
{

    public final static String RESOURCE_PATH = "SDE/items/abstrct/Decorations.yaml";
    private static LinkedHashMap<String, Decorations> cache = (null);

    @Override
    public int getGroupId() {
        return  937;
    }

    @Override
    public Class<?> getGroup() {
        return Decorations.class;
    }

    public static LinkedHashMap<String, Decorations> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Decorations.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Decorations> items;

    }

}
