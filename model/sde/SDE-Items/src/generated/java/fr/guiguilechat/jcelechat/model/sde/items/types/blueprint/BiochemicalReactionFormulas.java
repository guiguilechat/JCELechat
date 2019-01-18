package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class BiochemicalReactionFormulas
    extends Blueprint
{
    public static final BiochemicalReactionFormulas.MetaGroup METAGROUP = new BiochemicalReactionFormulas.MetaGroup();

    @Override
    public IMetaGroup<BiochemicalReactionFormulas> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<BiochemicalReactionFormulas>
    {
        public static final String RESOURCE_PATH = "SDE/items/blueprint/BiochemicalReactionFormulas.yaml";
        private Map<String, BiochemicalReactionFormulas> cache = (null);

        @Override
        public IMetaCategory<? super BiochemicalReactionFormulas> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1890;
        }

        @Override
        public String getName() {
            return "BiochemicalReactionFormulas";
        }

        @Override
        public synchronized Map<String, BiochemicalReactionFormulas> load() {
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
    }
}
