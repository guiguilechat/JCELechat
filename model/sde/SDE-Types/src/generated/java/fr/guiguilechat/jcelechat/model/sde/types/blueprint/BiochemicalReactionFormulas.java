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
import org.yaml.snakeyaml.Yaml;

public class BiochemicalReactionFormulas
    extends Blueprint
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final BiochemicalReactionFormulas.MetaGroup METAGROUP = new BiochemicalReactionFormulas.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<BiochemicalReactionFormulas> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<BiochemicalReactionFormulas>
    {
        public static final String RESOURCE_PATH = "SDE/types/blueprint/BiochemicalReactionFormulas.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(BiochemicalReactionFormulas.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, BiochemicalReactionFormulas> types;
        }
    }
}
