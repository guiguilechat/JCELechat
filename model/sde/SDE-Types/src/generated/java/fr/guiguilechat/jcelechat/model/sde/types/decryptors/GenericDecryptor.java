package fr.guiguilechat.jcelechat.model.sde.types.decryptors;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class GenericDecryptor
    extends Decryptors
{
    public static final GenericDecryptor.MetaGroup METAGROUP = new GenericDecryptor.MetaGroup();

    @Override
    public IMetaGroup<GenericDecryptor> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<GenericDecryptor>
    {
        public static final String RESOURCE_PATH = "SDE/items/decryptors/GenericDecryptor.yaml";
        private Map<String, GenericDecryptor> cache = (null);

        @Override
        public IMetaCategory<? super GenericDecryptor> category() {
            return Decryptors.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1304;
        }

        @Override
        public String getName() {
            return "GenericDecryptor";
        }

        @Override
        public synchronized Map<String, GenericDecryptor> load() {
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
    }
}
