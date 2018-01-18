
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class FWGallenteFederationDestroyer
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/FWGallenteFederationDestroyer.yaml";
    private static LinkedHashMap<String, FWGallenteFederationDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  1177;
    }

    @Override
    public Class<?> getGroup() {
        return FWGallenteFederationDestroyer.class;
    }

    public static LinkedHashMap<String, FWGallenteFederationDestroyer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(FWGallenteFederationDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, FWGallenteFederationDestroyer> items;

    }

}
