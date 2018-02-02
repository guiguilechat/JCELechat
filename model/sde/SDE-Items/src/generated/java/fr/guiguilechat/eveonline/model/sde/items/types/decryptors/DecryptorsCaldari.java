package fr.guiguilechat.eveonline.model.sde.items.types.decryptors;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsCaldari
    extends Decryptors
{
    public final static String RESOURCE_PATH = "SDE/items/decryptors/DecryptorsCaldari.yaml";
    private static LinkedHashMap<String, DecryptorsCaldari> cache = (null);

    @Override
    public int getGroupId() {
        return  731;
    }

    @Override
    public Class<?> getGroup() {
        return DecryptorsCaldari.class;
    }

    public static synchronized LinkedHashMap<String, DecryptorsCaldari> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DecryptorsCaldari.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DecryptorsCaldari> items;
    }
}
