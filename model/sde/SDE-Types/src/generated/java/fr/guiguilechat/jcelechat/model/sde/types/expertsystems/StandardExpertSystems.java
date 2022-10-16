package fr.guiguilechat.jcelechat.model.sde.types.expertsystems;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.ExpertSystems;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class StandardExpertSystems
    extends ExpertSystems
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final StandardExpertSystems.MetaGroup METAGROUP = new StandardExpertSystems.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<StandardExpertSystems> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StandardExpertSystems>
    {
        public static final String RESOURCE_PATH = "SDE/types/expertsystems/StandardExpertSystems.yaml";
        private Map<String, StandardExpertSystems> cache = (null);

        @Override
        public IMetaCategory<? super StandardExpertSystems> category() {
            return ExpertSystems.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4091;
        }

        @Override
        public String getName() {
            return "StandardExpertSystems";
        }

        @Override
        public synchronized Map<String, StandardExpertSystems> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StandardExpertSystems.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<String, StandardExpertSystems> types;
        }
    }
}
