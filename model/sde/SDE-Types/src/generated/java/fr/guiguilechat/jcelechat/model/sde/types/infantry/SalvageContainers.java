package fr.guiguilechat.jcelechat.model.sde.types.infantry;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Infantry;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class SalvageContainers
    extends Infantry
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final SalvageContainers.MetaGroup METAGROUP = new SalvageContainers.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<SalvageContainers> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SalvageContainers>
    {
        public static final String RESOURCE_PATH = "SDE/types/infantry/SalvageContainers.yaml";
        private Map<String, SalvageContainers> cache = (null);

        @Override
        public IMetaCategory<? super SalvageContainers> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  367774;
        }

        @Override
        public String getName() {
            return "SalvageContainers";
        }

        @Override
        public synchronized Map<String, SalvageContainers> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SalvageContainers.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<String, SalvageContainers> types;
        }
    }
}
