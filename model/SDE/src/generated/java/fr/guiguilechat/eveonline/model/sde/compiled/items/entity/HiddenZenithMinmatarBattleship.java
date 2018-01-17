
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithMinmatarBattleship
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithMinmatarBattleship.yaml";
    private static LinkedHashMap<String, HiddenZenithMinmatarBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  1798;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithMinmatarBattleship.class;
    }

    public static LinkedHashMap<String, HiddenZenithMinmatarBattleship> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithMinmatarBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, HiddenZenithMinmatarBattleship> items;

    }

}
