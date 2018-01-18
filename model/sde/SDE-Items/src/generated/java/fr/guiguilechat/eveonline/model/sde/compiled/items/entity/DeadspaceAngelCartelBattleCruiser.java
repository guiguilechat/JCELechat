
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceAngelCartelBattleCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceAngelCartelBattleCruiser.yaml";
    private static LinkedHashMap<String, DeadspaceAngelCartelBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  593;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceAngelCartelBattleCruiser.class;
    }

    public static LinkedHashMap<String, DeadspaceAngelCartelBattleCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceAngelCartelBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceAngelCartelBattleCruiser> items;

    }

}
