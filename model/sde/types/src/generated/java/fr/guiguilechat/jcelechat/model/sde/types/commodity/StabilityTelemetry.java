package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;

public class StabilityTelemetry
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final StabilityTelemetry.MetaGroup METAGROUP = new StabilityTelemetry.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<StabilityTelemetry> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StabilityTelemetry>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/StabilityTelemetry.yaml";
        private Map<Integer, StabilityTelemetry> cache = (null);

        @Override
        public IMetaCategory<? super StabilityTelemetry> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4900;
        }

        @Override
        public String getName() {
            return "StabilityTelemetry";
        }

        @Override
        public synchronized Map<Integer, StabilityTelemetry> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StabilityTelemetry.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, StabilityTelemetry> types;
        }
    }
}
