package fr.guiguilechat.jcelechat.model.sde.types.infrastructureupgrades;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.InfrastructureUpgrades;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class SovereigntyHubColonyResourcesManagementUpgrades
    extends InfrastructureUpgrades
{
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Capacity.INSTANCE })));
    public static final SovereigntyHubColonyResourcesManagementUpgrades.MetaGroup METAGROUP = new SovereigntyHubColonyResourcesManagementUpgrades.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<SovereigntyHubColonyResourcesManagementUpgrades> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SovereigntyHubColonyResourcesManagementUpgrades>
    {
        public static final String RESOURCE_PATH = "SDE/types/infrastructureupgrades/SovereigntyHubColonyResourcesManagementUpgrades.yaml";
        private Map<Integer, SovereigntyHubColonyResourcesManagementUpgrades> cache = (null);

        @Override
        public IMetaCategory<? super SovereigntyHubColonyResourcesManagementUpgrades> category() {
            return InfrastructureUpgrades.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4838;
        }

        @Override
        public String getName() {
            return "SovereigntyHubColonyResourcesManagementUpgrades";
        }

        @Override
        public synchronized Map<Integer, SovereigntyHubColonyResourcesManagementUpgrades> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SovereigntyHubColonyResourcesManagementUpgrades.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, SovereigntyHubColonyResourcesManagementUpgrades> types;
        }
    }
}
