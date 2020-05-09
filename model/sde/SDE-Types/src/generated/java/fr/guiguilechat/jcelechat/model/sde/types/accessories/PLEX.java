package fr.guiguilechat.jcelechat.model.sde.types.accessories;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Accessories;
import org.yaml.snakeyaml.Yaml;

public class PLEX
    extends Accessories
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final PLEX.MetaGroup METAGROUP = new PLEX.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<PLEX> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PLEX>
    {
        public static final String RESOURCE_PATH = "SDE/types/accessories/PLEX.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(PLEX.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PLEX> types;
        }
    }
}
