package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class OverseerPersonalEffects
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final OverseerPersonalEffects.MetaGroup METAGROUP = new OverseerPersonalEffects.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<OverseerPersonalEffects> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<OverseerPersonalEffects>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/OverseerPersonalEffects.yaml";
        private Map<String, OverseerPersonalEffects> cache = (null);

        @Override
        public IMetaCategory<? super OverseerPersonalEffects> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  493;
        }

        @Override
        public String getName() {
            return "OverseerPersonalEffects";
        }

        @Override
        public synchronized Map<String, OverseerPersonalEffects> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(OverseerPersonalEffects.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<String, OverseerPersonalEffects> types;
        }
    }
}
