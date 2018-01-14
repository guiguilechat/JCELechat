
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/AsteroidBloodRaidersCruiser.yaml";
    private static LinkedHashMap<String, AsteroidBloodRaidersCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  555;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidBloodRaidersCruiser.class;
    }

    public static LinkedHashMap<String, AsteroidBloodRaidersCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidBloodRaidersCruiser> items;

    }

}
