package fr.guiguilechat.jcelechat.model.sde.items.types.accessories;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Accessories;
import org.yaml.snakeyaml.Yaml;

public class PLEX
    extends Accessories
{
    public final static PLEX.MetaGroup METAGROUP = new PLEX.MetaGroup();

    @Override
    public IMetaGroup<PLEX> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PLEX>
    {
        public final static String RESOURCE_PATH = "SDE/items/accessories/PLEX.yaml";
        private Map<String, PLEX> cache = (null);

        @Override
        public IMetaCategory<? super PLEX> category() {
            return Accessories.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1875;
        }

        @Override
        public String getName() {
            return "PLEX";
        }

        @Override
        public synchronized Map<String, PLEX> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(PLEX.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PLEX> items;
        }
    }
}
