package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class CriminalTags
    extends Commodity
{
    public final static CriminalTags.MetaGroup METAGROUP = new CriminalTags.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/commodity/CriminalTags.yaml";
    private static Map<String, CriminalTags> cache = (null);

    @Override
    public int getGroupId() {
        return  370;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<CriminalTags> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, CriminalTags> load() {
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<CriminalTags>
    {

        @Override
        public MetaCategory<? super CriminalTags> category() {
            return Commodity.METACAT;
        }

        @Override
        public String getName() {
            return "CriminalTags";
        }

        @Override
        public Collection<CriminalTags> items() {
            return (load().values());
        }
    }
}
