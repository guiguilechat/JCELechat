package fr.guiguilechat.jcelechat.model.sde.types.infantry;

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
import fr.guiguilechat.jcelechat.model.sde.types.Infantry;

public class Services
    extends Infantry
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Services.MetaGroup METAGROUP = new Services.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Services> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Services>
    {
        public static final String RESOURCE_PATH = "SDE/types/infantry/Services.yaml";
        private Map<Integer, Services> cache = (null);

        @Override
        public IMetaCategory<? super Services> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  367487;
        }

        @Override
        public String getName() {
            return "Services";
        }

        @Override
        public synchronized Map<Integer, Services> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Services.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Services> types;
        }
    }
}
