package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.infrastructureupgrades.IndustrialUpgrades;
import fr.guiguilechat.jcelechat.model.sde.items.types.infrastructureupgrades.MilitaryUpgrades;
import fr.guiguilechat.jcelechat.model.sde.items.types.infrastructureupgrades.StrategicUpgrades;

public abstract class InfrastructureUpgrades
    extends Item
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
