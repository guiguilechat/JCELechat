
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class FWGallenteFederationBattlecruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/FWGallenteFederationBattlecruiser.yaml";
    private static LinkedHashMap<String, FWGallenteFederationBattlecruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1185;
    }

    @Override
    public Class<?> getGroup() {
        return FWGallenteFederationBattlecruiser.class;
    }

    public static LinkedHashMap<String, FWGallenteFederationBattlecruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(FWGallenteFederationBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, FWGallenteFederationBattlecruiser> items;

    }

}
