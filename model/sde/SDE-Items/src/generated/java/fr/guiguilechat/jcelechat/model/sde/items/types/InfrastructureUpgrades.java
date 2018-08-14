package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.infrastructureupgrades.IndustrialUpgrades;
import fr.guiguilechat.jcelechat.model.sde.items.types.infrastructureupgrades.MilitaryUpgrades;
import fr.guiguilechat.jcelechat.model.sde.items.types.infrastructureupgrades.StrategicUpgrades;

public abstract class InfrastructureUpgrades
    extends Item
{
    public final static InfrastructureUpgrades.MetaCat METACAT = new InfrastructureUpgrades.MetaCat();

    @Override
    public IMetaCategory<InfrastructureUpgrades> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<InfrastructureUpgrades>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends InfrastructureUpgrades> [] groups = new IMetaGroup[] {StrategicUpgrades.METAGROUP, IndustrialUpgrades.METAGROUP, MilitaryUpgrades.METAGROUP };

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
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, InfrastructureUpgrades> load() {
            HashMap<String, InfrastructureUpgrades> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
