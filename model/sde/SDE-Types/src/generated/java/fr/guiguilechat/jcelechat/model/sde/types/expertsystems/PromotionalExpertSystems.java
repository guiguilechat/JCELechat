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

public class PromotionalExpertSystems
    extends ExpertSystems
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final PromotionalExpertSystems.MetaGroup METAGROUP = new PromotionalExpertSystems.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<PromotionalExpertSystems> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PromotionalExpertSystems>
    {
        public static final String RESOURCE_PATH = "SDE/types/expertsystems/PromotionalExpertSystems.yaml";
        private Map<Integer, PromotionalExpertSystems> cache = (null);

        @Override
        public IMetaCategory<? super PromotionalExpertSystems> category() {
            return ExpertSystems.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4098;
        }

        @Override
        public String getName() {
            return "PromotionalExpertSystems";
        }

        @Override
        public synchronized Map<Integer, PromotionalExpertSystems> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PromotionalExpertSystems.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, PromotionalExpertSystems> types;
        }
    }
}
