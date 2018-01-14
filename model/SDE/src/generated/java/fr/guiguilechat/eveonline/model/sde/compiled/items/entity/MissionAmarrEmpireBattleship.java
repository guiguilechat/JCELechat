
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionAmarrEmpireBattleship
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/MissionAmarrEmpireBattleship.yaml";
    private static LinkedHashMap<String, MissionAmarrEmpireBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  667;
    }

    @Override
    public Class<?> getGroup() {
        return MissionAmarrEmpireBattleship.class;
    }

    public static LinkedHashMap<String, MissionAmarrEmpireBattleship> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionAmarrEmpireBattleship> items;

    }

}
