package fr.guiguilechat.jcelechat.model.sde.types.infrastructureupgrades;

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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.DevIndexSovereignty;
import fr.guiguilechat.jcelechat.model.sde.attributes.SovBillSystemCostDEPRECATED;
import fr.guiguilechat.jcelechat.model.sde.types.InfrastructureUpgrades;

public class SovereigntyHubServiceInfrastructureUpgrade
    extends InfrastructureUpgrades
{
    /**
     * The minimum required sovereignty index level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int devindexsovereignty;
    /**
     * [DEPRECATED] The sum of this attribute on the claim markers, Infrastructure hub, and each upgrade is the systems base cost. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int sovbillsystemcostdeprecated;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {SovBillSystemCostDEPRECATED.INSTANCE, DevIndexSovereignty.INSTANCE })));
    public static final SovereigntyHubServiceInfrastructureUpgrade.MetaGroup METAGROUP = new SovereigntyHubServiceInfrastructureUpgrade.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1615 :
            {
                return devindexsovereignty;
            }
            case  1603 :
            {
                return sovbillsystemcostdeprecated;
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
    public IMetaGroup<SovereigntyHubServiceInfrastructureUpgrade> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SovereigntyHubServiceInfrastructureUpgrade>
    {
        public static final String RESOURCE_PATH = "SDE/types/infrastructureupgrades/SovereigntyHubServiceInfrastructureUpgrade.yaml";
        private Map<Integer, SovereigntyHubServiceInfrastructureUpgrade> cache = (null);

        @Override
        public IMetaCategory<? super SovereigntyHubServiceInfrastructureUpgrade> category() {
            return InfrastructureUpgrades.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4772;
        }

        @Override
        public String getName() {
            return "SovereigntyHubServiceInfrastructureUpgrade";
        }

        @Override
        public synchronized Map<Integer, SovereigntyHubServiceInfrastructureUpgrade> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SovereigntyHubServiceInfrastructureUpgrade.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, SovereigntyHubServiceInfrastructureUpgrade> types;
        }
    }
}
