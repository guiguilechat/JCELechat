
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingBloodRaiderCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/RoamingBloodRaiderCruiser.yaml";
    private static LinkedHashMap<String, RoamingBloodRaiderCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1735;
    }

    @Override
    public Class<?> getGroup() {
        return RoamingBloodRaiderCruiser.class;
    }

    public static LinkedHashMap<String, RoamingBloodRaiderCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RoamingBloodRaiderCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, RoamingBloodRaiderCruiser> items;

    }

}
