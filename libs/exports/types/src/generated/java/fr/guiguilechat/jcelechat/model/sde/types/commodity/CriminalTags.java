package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class CriminalTags
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final CriminalTags.MetaGroup METAGROUP = new CriminalTags.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<CriminalTags> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CriminalTags>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/CriminalTags.yaml";
        private Map<Integer, CriminalTags> cache = (null);

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
        public synchronized Map<Integer, CriminalTags> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CriminalTags.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, CriminalTags> types;
        }
    }
}
