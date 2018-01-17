
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class BloodRaiderEventBattlecruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/BloodRaiderEventBattlecruiser.yaml";
    private static LinkedHashMap<String, BloodRaiderEventBattlecruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1455;
    }

    @Override
    public Class<?> getGroup() {
        return BloodRaiderEventBattlecruiser.class;
    }

    public static LinkedHashMap<String, BloodRaiderEventBattlecruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(BloodRaiderEventBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, BloodRaiderEventBattlecruiser> items;

    }

}
