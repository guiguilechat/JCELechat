package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class PolymerReactionFormulas
    extends Blueprint
{
    public final static PolymerReactionFormulas.MetaGroup METAGROUP = new PolymerReactionFormulas.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/blueprint/PolymerReactionFormulas.yaml";
    private static Map<String, PolymerReactionFormulas> cache = (null);

    @Override
    public int getGroupId() {
        return  1889;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<PolymerReactionFormulas> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, PolymerReactionFormulas> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PolymerReactionFormulas.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, PolymerReactionFormulas> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<PolymerReactionFormulas>
    {

        @Override
        public MetaCategory<? super PolymerReactionFormulas> category() {
            return Blueprint.METACAT;
        }

        @Override
        public String getName() {
            return "PolymerReactionFormulas";
        }

        @Override
        public Collection<PolymerReactionFormulas> items() {
            return (load().values());
        }
    }
}
