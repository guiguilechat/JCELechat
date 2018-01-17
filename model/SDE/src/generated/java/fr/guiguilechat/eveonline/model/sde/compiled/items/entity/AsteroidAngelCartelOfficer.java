
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelOfficer
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelOfficer.yaml";
    private static LinkedHashMap<String, AsteroidAngelCartelOfficer> cache = (null);

    @Override
    public int getGroupId() {
        return  553;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidAngelCartelOfficer.class;
    }

    public static LinkedHashMap<String, AsteroidAngelCartelOfficer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelOfficer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidAngelCartelOfficer> items;

    }

}
