package fr.guiguilechat.jcelechat.model.sde.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class CompositeReactionFormulas
    extends Blueprint
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final CompositeReactionFormulas.MetaGroup METAGROUP = new CompositeReactionFormulas.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<CompositeReactionFormulas> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CompositeReactionFormulas>
    {
        public static final String RESOURCE_PATH = "SDE/types/blueprint/CompositeReactionFormulas.yaml";
        private Map<Integer, CompositeReactionFormulas> cache = (null);

        @Override
        public IMetaCategory<? super CompositeReactionFormulas> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1888;
        }

        @Override
        public String getName() {
            return "CompositeReactionFormulas";
        }

        @Override
        public synchronized Map<Integer, CompositeReactionFormulas> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CompositeReactionFormulas.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, CompositeReactionFormulas> types;
        }
    }
}
