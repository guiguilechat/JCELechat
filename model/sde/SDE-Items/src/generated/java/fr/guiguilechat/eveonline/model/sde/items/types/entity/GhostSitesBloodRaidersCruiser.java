
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class GhostSitesBloodRaidersCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/GhostSitesBloodRaidersCruiser.yaml";
    private static LinkedHashMap<String, GhostSitesBloodRaidersCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1255;
    }

    @Override
    public Class<?> getGroup() {
        return GhostSitesBloodRaidersCruiser.class;
    }

    public static LinkedHashMap<String, GhostSitesBloodRaidersCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(GhostSitesBloodRaidersCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, GhostSitesBloodRaidersCruiser> items;

    }

}
