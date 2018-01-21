
package fr.guiguilechat.eveonline.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class AncientSalvage
    extends Material
{

    /**
     * The maximum hitpoints of an object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    public final static String RESOURCE_PATH = "SDE/items/material/AncientSalvage.yaml";
    private static LinkedHashMap<String, AncientSalvage> cache = (null);

    @Override
    public int getGroupId() {
        return  966;
    }

    @Override
    public Class<?> getGroup() {
        return AncientSalvage.class;
    }

    public static LinkedHashMap<String, AncientSalvage> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AncientSalvage.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AncientSalvage> items;

    }

}
