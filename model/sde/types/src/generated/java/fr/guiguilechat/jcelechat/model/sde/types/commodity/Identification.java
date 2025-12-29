package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;

public class Identification
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Identification.MetaGroup METAGROUP = new Identification.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Identification> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Identification>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/Identification.yaml";
        private Map<Integer, Identification> cache = (null);

        @Override
        public IMetaCategory<? super Identification> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  521;
        }

        @Override
        public String getName() {
            return "Identification";
        }

        @Override
        public synchronized Map<Integer, Identification> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Identification.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Identification> types;
        }
    }
}
