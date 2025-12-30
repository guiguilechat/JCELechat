package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.infrastructureupgrades.InfrastructureHubIndustrialUpgrades;
import fr.guiguilechat.jcelechat.model.sde.types.infrastructureupgrades.InfrastructureHubMilitaryUpgrades;
import fr.guiguilechat.jcelechat.model.sde.types.infrastructureupgrades.InfrastructureHubStrategicUpgrades;
import fr.guiguilechat.jcelechat.model.sde.types.infrastructureupgrades.SovereigntyHubColonyResourcesManagementUpgrades;
import fr.guiguilechat.jcelechat.model.sde.types.infrastructureupgrades.SovereigntyHubServiceInfrastructureUpgrade;
import fr.guiguilechat.jcelechat.model.sde.types.infrastructureupgrades.SovereigntyHubSiteDetectionUpgrades;
import fr.guiguilechat.jcelechat.model.sde.types.infrastructureupgrades.SovereigntyHubSystemEffectGeneratorUpgrades;

public abstract class InfrastructureUpgrades
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final InfrastructureUpgrades.MetaCat METACAT = new InfrastructureUpgrades.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<InfrastructureUpgrades> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<InfrastructureUpgrades>
    {

        @Override
        public int getCategoryId() {
            return  39;
        }

        @Override
        public String getName() {
            return "InfrastructureUpgrades";
        }

        @Override
        public Collection<IMetaGroup<? extends InfrastructureUpgrades>> groups() {
            return Arrays.asList(SovereigntyHubSiteDetectionUpgrades.METAGROUP, SovereigntyHubServiceInfrastructureUpgrade.METAGROUP, SovereigntyHubColonyResourcesManagementUpgrades.METAGROUP, SovereigntyHubSystemEffectGeneratorUpgrades.METAGROUP, InfrastructureHubStrategicUpgrades.METAGROUP, InfrastructureHubIndustrialUpgrades.METAGROUP, InfrastructureHubMilitaryUpgrades.METAGROUP);
        }
    }
}
