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

public class PolymerReactionFormulas
    extends Blueprint
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final PolymerReactionFormulas.MetaGroup METAGROUP = new PolymerReactionFormulas.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<PolymerReactionFormulas> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PolymerReactionFormulas>
    {
        public static final String RESOURCE_PATH = "SDE/types/blueprint/PolymerReactionFormulas.yaml";
        private Map<Integer, PolymerReactionFormulas> cache = (null);

        @Override
        public IMetaCategory<? super PolymerReactionFormulas> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1889;
        }

        @Override
        public String getName() {
            return "PolymerReactionFormulas";
        }

        @Override
        public synchronized Map<Integer, PolymerReactionFormulas> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PolymerReactionFormulas.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, PolymerReactionFormulas> types;
        }
    }
}
