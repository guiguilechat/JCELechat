package fr.guiguilechat.jcelechat.model.sde.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExportTaxMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImportTaxMultiplier;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryCommodities;

public class BasicCommoditiesTier1
    extends PlanetaryCommodities
{
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ImportTaxMultiplier.INSTANCE, ExportTaxMultiplier.INSTANCE })));
    public static final BasicCommoditiesTier1 .MetaGroup METAGROUP = new BasicCommoditiesTier1 .MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<BasicCommoditiesTier1> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<BasicCommoditiesTier1>
    {
        public static final String RESOURCE_PATH = "SDE/types/planetarycommodities/BasicCommoditiesTier1.yaml";
        private Map<Integer, BasicCommoditiesTier1> cache = (null);

        @Override
        public IMetaCategory<? super BasicCommoditiesTier1> category() {
            return PlanetaryCommodities.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1042;
        }

        @Override
        public String getName() {
            return "BasicCommoditiesTier1";
        }

        @Override
        public synchronized Map<Integer, BasicCommoditiesTier1> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(BasicCommoditiesTier1 .MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, BasicCommoditiesTier1> types;
        }
    }
}
