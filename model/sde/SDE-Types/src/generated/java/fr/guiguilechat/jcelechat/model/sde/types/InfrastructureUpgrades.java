package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.infrastructureupgrades.IndustrialUpgrades;
import fr.guiguilechat.jcelechat.model.sde.types.infrastructureupgrades.MilitaryUpgrades;
import fr.guiguilechat.jcelechat.model.sde.types.infrastructureupgrades.StrategicUpgrades;

public abstract class InfrastructureUpgrades
    extends EveType
{
    public static final InfrastructureUpgrades.MetaCat METACAT = new InfrastructureUpgrades.MetaCat();

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
            return Arrays.asList(StrategicUpgrades.METAGROUP, IndustrialUpgrades.METAGROUP, MilitaryUpgrades.METAGROUP);
        }
    }
}
