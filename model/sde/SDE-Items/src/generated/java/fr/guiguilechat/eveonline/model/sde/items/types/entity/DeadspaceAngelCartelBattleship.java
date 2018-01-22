package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceAngelCartelBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceAngelCartelBattleship.yaml";
    private static LinkedHashMap<String, DeadspaceAngelCartelBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  594;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceAngelCartelBattleship.class;
    }

    public static LinkedHashMap<String, DeadspaceAngelCartelBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceAngelCartelBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceAngelCartelBattleship> items;
    }
}
