package fr.guiguilechat.eveonline.model.sde.items.types.decryptors;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsGallente
    extends Decryptors
{
    public final static String RESOURCE_PATH = "SDE/items/decryptors/DecryptorsGallente.yaml";
    private static LinkedHashMap<String, DecryptorsGallente> cache = (null);

    @Override
    public int getGroupId() {
        return  730;
    }

    @Override
    public Class<?> getGroup() {
        return DecryptorsGallente.class;
    }

    public static synchronized LinkedHashMap<String, DecryptorsGallente> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DecryptorsGallente.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DecryptorsGallente> items;
    }
}
