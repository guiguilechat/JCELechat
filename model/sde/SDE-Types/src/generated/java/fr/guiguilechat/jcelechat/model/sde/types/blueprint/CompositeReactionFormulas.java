package fr.guiguilechat.jcelechat.model.sde.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class CompositeReactionFormulas
    extends Blueprint
{
    public static final CompositeReactionFormulas.MetaGroup METAGROUP = new CompositeReactionFormulas.MetaGroup();

    @Override
    public IMetaGroup<CompositeReactionFormulas> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CompositeReactionFormulas>
    {
        public static final String RESOURCE_PATH = "SDE/items/blueprint/CompositeReactionFormulas.yaml";
        private Map<String, CompositeReactionFormulas> cache = (null);

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
        public synchronized Map<String, CompositeReactionFormulas> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(CompositeReactionFormulas.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CompositeReactionFormulas> items;
        }
    }
}
