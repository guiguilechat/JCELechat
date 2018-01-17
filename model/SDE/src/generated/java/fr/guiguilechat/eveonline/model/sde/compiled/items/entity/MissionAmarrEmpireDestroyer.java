
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionAmarrEmpireDestroyer
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionAmarrEmpireDestroyer.yaml";
    private static LinkedHashMap<String, MissionAmarrEmpireDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  669;
    }

    @Override
    public Class<?> getGroup() {
        return MissionAmarrEmpireDestroyer.class;
    }

    public static LinkedHashMap<String, MissionAmarrEmpireDestroyer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionAmarrEmpireDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionAmarrEmpireDestroyer> items;

    }

}
