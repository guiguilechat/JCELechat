package fr.guiguilechat.jcelechat.model.sde.types.material;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Material;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class NamedComponents
    extends Material
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final NamedComponents.MetaGroup METAGROUP = new NamedComponents.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<NamedComponents> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NamedComponents>
    {
        public static final String RESOURCE_PATH = "SDE/types/material/NamedComponents.yaml";
        private Map<Integer, NamedComponents> cache = (null);

        @Override
        public IMetaCategory<? super NamedComponents> category() {
            return Material.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1676;
        }

        @Override
        public String getName() {
            return "NamedComponents";
        }

        @Override
        public synchronized Map<Integer, NamedComponents> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(NamedComponents.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, NamedComponents> types;
        }
    }
}
