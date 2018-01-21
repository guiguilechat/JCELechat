
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class GhostSitesSerpentisCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/GhostSitesSerpentisCruiser.yaml";
    private static LinkedHashMap<String, GhostSitesSerpentisCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1262;
    }

    @Override
    public Class<?> getGroup() {
        return GhostSitesSerpentisCruiser.class;
    }

    public static LinkedHashMap<String, GhostSitesSerpentisCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(GhostSitesSerpentisCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, GhostSitesSerpentisCruiser> items;

    }

}
