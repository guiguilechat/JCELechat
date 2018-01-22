package fr.guiguilechat.eveonline.model.sde.items.types.decryptors;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsHybrid
    extends Decryptors
{
    public final static String RESOURCE_PATH = "SDE/items/decryptors/DecryptorsHybrid.yaml";
    private static LinkedHashMap<String, DecryptorsHybrid> cache = (null);

    @Override
    public int getGroupId() {
        return  979;
    }

    @Override
    public Class<?> getGroup() {
        return DecryptorsHybrid.class;
    }

    public static LinkedHashMap<String, DecryptorsHybrid> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DecryptorsHybrid.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DecryptorsHybrid> items;
    }
}
