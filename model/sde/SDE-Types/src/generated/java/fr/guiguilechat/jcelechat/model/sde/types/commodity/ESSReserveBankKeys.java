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
import org.yaml.snakeyaml.Yaml;

public class ESSReserveBankKeys
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final ESSReserveBankKeys.MetaGroup METAGROUP = new ESSReserveBankKeys.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<ESSReserveBankKeys> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ESSReserveBankKeys>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/ESSReserveBankKeys.yaml";
        private Map<String, ESSReserveBankKeys> cache = (null);

        @Override
        public IMetaCategory<? super ESSReserveBankKeys> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4102;
        }

        @Override
        public String getName() {
            return "ESSReserveBankKeys";
        }

        @Override
        public synchronized Map<String, ESSReserveBankKeys> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ESSReserveBankKeys.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ESSReserveBankKeys> types;
        }
    }
}
