package fr.guiguilechat.jcelechat.model.sde.types.decryptors;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsHybrid
    extends Decryptors
{
    public static final DecryptorsHybrid.MetaGroup METAGROUP = new DecryptorsHybrid.MetaGroup();

    @Override
    public IMetaGroup<DecryptorsHybrid> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DecryptorsHybrid>
    {
        public static final String RESOURCE_PATH = "SDE/items/decryptors/DecryptorsHybrid.yaml";
        private Map<String, DecryptorsHybrid> cache = (null);

        @Override
        public IMetaCategory<? super DecryptorsHybrid> category() {
            return Decryptors.METACAT;
        }

        @Override
        public int getGroupId() {
            return  979;
        }

        @Override
        public String getName() {
            return "DecryptorsHybrid";
        }

        @Override
        public synchronized Map<String, DecryptorsHybrid> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DecryptorsHybrid.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DecryptorsHybrid> items;
        }
    }
}
