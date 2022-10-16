package fr.guiguilechat.jcelechat.model.sde.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExportTaxMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImportTaxMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class RefinedCommoditiesTier2
    extends PlanetaryCommodities
{
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Capacity.INSTANCE, ImportTaxMultiplier.INSTANCE, ExportTaxMultiplier.INSTANCE })));
    public static final RefinedCommoditiesTier2 .MetaGroup METAGROUP = new RefinedCommoditiesTier2 .MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<RefinedCommoditiesTier2> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RefinedCommoditiesTier2>
    {
        public static final String RESOURCE_PATH = "SDE/types/planetarycommodities/RefinedCommoditiesTier2.yaml";
        private Map<String, RefinedCommoditiesTier2> cache = (null);

        @Override
        public IMetaCategory<? super RefinedCommoditiesTier2> category() {
            return PlanetaryCommodities.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1034;
        }

        @Override
        public String getName() {
            return "RefinedCommoditiesTier2";
        }

        @Override
        public synchronized Map<String, RefinedCommoditiesTier2> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(RefinedCommoditiesTier2 .MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RefinedCommoditiesTier2> types;
        }
    }
}
