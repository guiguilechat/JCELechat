
package fr.guiguilechat.eveonline.model.sde.compiled.items.bonus;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Bonus;
import org.yaml.snakeyaml.Yaml;

public class PhobiaHandicap
    extends Bonus
{

    public final static String RESOURCE_PATH = "SDE/bonus/PhobiaHandicap.yaml";
    private static LinkedHashMap<String, PhobiaHandicap> cache = (null);

    @Override
    public int getGroupId() {
        return  193;
    }

    @Override
    public Class<?> getGroup() {
        return PhobiaHandicap.class;
    }

    public static LinkedHashMap<String, PhobiaHandicap> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, PhobiaHandicap> items;

    }

}
