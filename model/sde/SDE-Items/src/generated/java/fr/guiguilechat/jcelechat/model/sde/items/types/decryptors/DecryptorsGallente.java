package fr.guiguilechat.jcelechat.model.sde.items.types.decryptors;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsGallente
    extends Decryptors
{
    public static final DecryptorsGallente.MetaGroup METAGROUP = new DecryptorsGallente.MetaGroup();

    @Override
    public IMetaGroup<DecryptorsGallente> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DecryptorsGallente>
    {
        public static final String RESOURCE_PATH = "SDE/items/decryptors/DecryptorsGallente.yaml";
        private Map<String, DecryptorsGallente> cache = (null);

        @Override
        public IMetaCategory<? super DecryptorsGallente> category() {
            return Decryptors.METACAT;
        }

        @Override
        public int getGroupId() {
            return  730;
        }

        @Override
        public String getName() {
            return "DecryptorsGallente";
        }

        @Override
        public synchronized Map<String, DecryptorsGallente> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DecryptorsGallente.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DecryptorsGallente> items;
        }
    }
}
