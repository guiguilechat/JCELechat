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

public class ShipSKINDesignElement
    extends Personalization
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final ShipSKINDesignElement.MetaGroup METAGROUP = new ShipSKINDesignElement.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<ShipSKINDesignElement> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ShipSKINDesignElement>
    {
        public static final String RESOURCE_PATH = "SDE/types/personalization/ShipSKINDesignElement.yaml";
        private Map<Integer, ShipSKINDesignElement> cache = (null);

        @Override
        public IMetaCategory<? super ShipSKINDesignElement> category() {
            return Personalization.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4726;
        }

        @Override
        public String getName() {
            return "ShipSKINDesignElement";
        }

        @Override
        public synchronized Map<Integer, ShipSKINDesignElement> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ShipSKINDesignElement.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, ShipSKINDesignElement> types;
        }
    }
}
