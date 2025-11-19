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
import fr.guiguilechat.jcelechat.model.sde.attributes.ExportTaxMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImportTaxMultiplier;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryCommodities;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class SpecializedCommoditiesTier3
    extends PlanetaryCommodities
{
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ImportTaxMultiplier.INSTANCE, ExportTaxMultiplier.INSTANCE })));
    public static final SpecializedCommoditiesTier3 .MetaGroup METAGROUP = new SpecializedCommoditiesTier3 .MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<SpecializedCommoditiesTier3> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SpecializedCommoditiesTier3>
    {
        public static final String RESOURCE_PATH = "SDE/types/planetarycommodities/SpecializedCommoditiesTier3.yaml";
        private Map<Integer, SpecializedCommoditiesTier3> cache = (null);

        @Override
        public IMetaCategory<? super SpecializedCommoditiesTier3> category() {
            return PlanetaryCommodities.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1040;
        }

        @Override
        public String getName() {
            return "SpecializedCommoditiesTier3";
        }

        @Override
        public synchronized Map<Integer, SpecializedCommoditiesTier3> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SpecializedCommoditiesTier3 .MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, SpecializedCommoditiesTier3> types;
        }
    }
}
