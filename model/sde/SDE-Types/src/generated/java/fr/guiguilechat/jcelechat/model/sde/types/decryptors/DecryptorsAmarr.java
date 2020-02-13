package fr.guiguilechat.jcelechat.model.sde.types.decryptors;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsAmarr
    extends Decryptors
{
    public static final DecryptorsAmarr.MetaGroup METAGROUP = new DecryptorsAmarr.MetaGroup();

    @Override
    public IMetaGroup<DecryptorsAmarr> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DecryptorsAmarr>
    {
        public static final String RESOURCE_PATH = "SDE/items/decryptors/DecryptorsAmarr.yaml";
        private Map<String, DecryptorsAmarr> cache = (null);

        @Override
        public IMetaCategory<? super DecryptorsAmarr> category() {
            return Decryptors.METACAT;
        }

        @Override
        public int getGroupId() {
            return  728;
        }

        @Override
        public String getName() {
            return "DecryptorsAmarr";
        }

        @Override
        public synchronized Map<String, DecryptorsAmarr> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DecryptorsAmarr.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DecryptorsAmarr> items;
        }
    }
}
