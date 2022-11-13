package fr.guiguilechat.jcelechat.model.sde.types.personalization;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Personalization;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class ShipPersonalization
    extends Personalization
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final ShipPersonalization.MetaGroup METAGROUP = new ShipPersonalization.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<ShipPersonalization> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ShipPersonalization>
    {
        public static final String RESOURCE_PATH = "SDE/types/personalization/ShipPersonalization.yaml";
        private Map<Integer, ShipPersonalization> cache = (null);

        @Override
        public IMetaCategory<? super ShipPersonalization> category() {
            return Personalization.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4471;
        }

        @Override
        public String getName() {
            return "ShipPersonalization";
        }

        @Override
        public synchronized Map<Integer, ShipPersonalization> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ShipPersonalization.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, ShipPersonalization> types;
        }
    }
}
