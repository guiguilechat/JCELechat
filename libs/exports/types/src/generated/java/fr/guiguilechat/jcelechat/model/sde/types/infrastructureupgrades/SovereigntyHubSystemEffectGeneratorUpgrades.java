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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionID01;
import fr.guiguilechat.jcelechat.model.sde.types.InfrastructureUpgrades;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class SovereigntyHubSystemEffectGeneratorUpgrades
    extends InfrastructureUpgrades
{
    /**
     * Distribution ID for global sov upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionid01;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {DistributionID01 .INSTANCE })));
    public static final SovereigntyHubSystemEffectGeneratorUpgrades.MetaGroup METAGROUP = new SovereigntyHubSystemEffectGeneratorUpgrades.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1755 :
            {
                return distributionid01;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<SovereigntyHubSystemEffectGeneratorUpgrades> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SovereigntyHubSystemEffectGeneratorUpgrades>
    {
        public static final String RESOURCE_PATH = "SDE/types/infrastructureupgrades/SovereigntyHubSystemEffectGeneratorUpgrades.yaml";
        private Map<Integer, SovereigntyHubSystemEffectGeneratorUpgrades> cache = (null);

        @Override
        public IMetaCategory<? super SovereigntyHubSystemEffectGeneratorUpgrades> category() {
            return InfrastructureUpgrades.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4839;
        }

        @Override
        public String getName() {
            return "SovereigntyHubSystemEffectGeneratorUpgrades";
        }

        @Override
        public synchronized Map<Integer, SovereigntyHubSystemEffectGeneratorUpgrades> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SovereigntyHubSystemEffectGeneratorUpgrades.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, SovereigntyHubSystemEffectGeneratorUpgrades> types;
        }
    }
}
