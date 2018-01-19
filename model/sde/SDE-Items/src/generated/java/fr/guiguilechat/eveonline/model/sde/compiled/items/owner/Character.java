
package fr.guiguilechat.eveonline.model.sde.compiled.items.owner;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Owner;
import org.yaml.snakeyaml.Yaml;

public class Character
    extends Owner
{

    public final static String RESOURCE_PATH = "SDE/items/owner/Character.yaml";
    private static LinkedHashMap<String, Character> cache = (null);

    @Override
    public int getGroupId() {
        return  1;
    }

    @Override
    public Class<?> getGroup() {
        return Character.class;
    }

    public static LinkedHashMap<String, Character> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Character.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Character> items;

    }

}