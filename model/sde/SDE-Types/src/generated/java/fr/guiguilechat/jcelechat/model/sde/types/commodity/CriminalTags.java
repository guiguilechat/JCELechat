package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class CriminalTags
    extends Commodity
{
    public static final CriminalTags.MetaGroup METAGROUP = new CriminalTags.MetaGroup();

    @Override
    public IMetaGroup<CriminalTags> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CriminalTags>
    {
        public static final String RESOURCE_PATH = "SDE/items/commodity/CriminalTags.yaml";
        private Map<String, CriminalTags> cache = (null);

        @Override
        public IMetaCategory<? super CriminalTags> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  370;
        }

        @Override
        public String getName() {
            return "CriminalTags";
        }

        @Override
        public synchronized Map<String, CriminalTags> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(CriminalTags.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CriminalTags> items;
        }
    }
}
