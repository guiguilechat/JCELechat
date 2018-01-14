
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class GhostSitesSerpentisCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/GhostSitesSerpentisCruiser.yaml";
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
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, GhostSitesSerpentisCruiser> items;

    }

}
