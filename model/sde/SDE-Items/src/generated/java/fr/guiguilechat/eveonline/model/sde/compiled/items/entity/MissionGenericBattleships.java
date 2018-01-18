
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGenericBattleships
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionGenericBattleships.yaml";
    private static LinkedHashMap<String, MissionGenericBattleships> cache = (null);

    @Override
    public int getGroupId() {
        return  816;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGenericBattleships.class;
    }

    public static LinkedHashMap<String, MissionGenericBattleships> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionGenericBattleships.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionGenericBattleships> items;

    }

}
