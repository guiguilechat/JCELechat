package fr.guiguilechat.jcelechat.model.sde.items.types.decryptors;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class GenericDecryptor
    extends Decryptors
{
    public final static GenericDecryptor.MetaGroup METAGROUP = new GenericDecryptor.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/decryptors/GenericDecryptor.yaml";
    private static Map<String, GenericDecryptor> cache = (null);

    @Override
    public int getGroupId() {
        return  1304;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<GenericDecryptor> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, GenericDecryptor> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(GenericDecryptor.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, GenericDecryptor> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<GenericDecryptor>
    {

        @Override
        public MetaCategory<? super GenericDecryptor> category() {
            return Decryptors.METACAT;
        }

        @Override
        public String getName() {
            return "GenericDecryptor";
        }

        @Override
        public Collection<GenericDecryptor> items() {
            return (load().values());
        }
    }
}
