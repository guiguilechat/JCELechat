package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class BiochemicalReactionFormulas
    extends Blueprint
{
    public final static BiochemicalReactionFormulas.MetaGroup METAGROUP = new BiochemicalReactionFormulas.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/blueprint/BiochemicalReactionFormulas.yaml";
    private static Map<String, BiochemicalReactionFormulas> cache = (null);

    @Override
    public int getGroupId() {
        return  1890;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<BiochemicalReactionFormulas> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, BiochemicalReactionFormulas> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(BiochemicalReactionFormulas.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, BiochemicalReactionFormulas> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<BiochemicalReactionFormulas>
    {

        @Override
        public MetaCategory<? super BiochemicalReactionFormulas> category() {
            return Blueprint.METACAT;
        }

        @Override
        public String getName() {
            return "BiochemicalReactionFormulas";
        }

        @Override
        public Collection<BiochemicalReactionFormulas> items() {
            return (load().values());
        }
    }
}
