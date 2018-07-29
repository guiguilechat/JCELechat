package fr.guiguilechat.jcelechat.model.sde.items.types.decryptors;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Decryptors;

public class DecryptorsMinmatar
    extends Decryptors
{
    public final static String RESOURCE_PATH = "SDE/items/decryptors/DecryptorsMinmatar.yaml";
    private static LinkedHashMap<String, DecryptorsMinmatar> cache = (null);

    @Override
    public int getGroupId() {
        return  729;
    }

    @Override
    public Class<?> getGroup() {
        return DecryptorsMinmatar.class;
    }

    public static synchronized LinkedHashMap<String, DecryptorsMinmatar> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DecryptorsMinmatar.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DecryptorsMinmatar> items;
    }
}
