package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class CompositeReactionFormulas
    extends Blueprint
{
    public final static CompositeReactionFormulas.MetaGroup METAGROUP = new CompositeReactionFormulas.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/blueprint/CompositeReactionFormulas.yaml";
    private static Map<String, CompositeReactionFormulas> cache = (null);

    @Override
    public int getGroupId() {
        return  1888;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<CompositeReactionFormulas> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, CompositeReactionFormulas> load() {
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<CompositeReactionFormulas>
    {

        @Override
        public MetaCategory<? super CompositeReactionFormulas> category() {
            return Blueprint.METACAT;
        }

        @Override
        public String getName() {
            return "CompositeReactionFormulas";
        }

        @Override
        public Collection<CompositeReactionFormulas> items() {
            return (load().values());
        }
    }
}
