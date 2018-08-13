package fr.guiguilechat.jcelechat.model.sde.items.types.accessories;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Accessories;
import org.yaml.snakeyaml.Yaml;

public class PLEX
    extends Accessories
{
    public final static PLEX.MetaGroup METAGROUP = new PLEX.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/accessories/PLEX.yaml";
    private static Map<String, PLEX> cache = (null);

    @Override
    public int getGroupId() {
        return  1875;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<PLEX> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, PLEX> load() {
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<PLEX>
    {

        @Override
        public MetaCategory<? super PLEX> category() {
            return Accessories.METACAT;
        }

        @Override
        public String getName() {
            return "PLEX";
        }

        @Override
        public Collection<PLEX> items() {
            return (load().values());
        }
    }
}
