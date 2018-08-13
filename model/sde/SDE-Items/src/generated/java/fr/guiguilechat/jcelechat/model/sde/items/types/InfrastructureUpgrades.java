package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.infrastructureupgrades.IndustrialUpgrades;
import fr.guiguilechat.jcelechat.model.sde.items.types.infrastructureupgrades.MilitaryUpgrades;
import fr.guiguilechat.jcelechat.model.sde.items.types.infrastructureupgrades.StrategicUpgrades;

public abstract class InfrastructureUpgrades
    extends Item
{
    public final static InfrastructureUpgrades.MetaCat METACAT = new InfrastructureUpgrades.MetaCat();

    @Override
    public int getCategoryId() {
        return  39;
    }

    @Override
    public MetaCategory<InfrastructureUpgrades> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends InfrastructureUpgrades> loadCategory() {
        return Stream.of(IndustrialUpgrades.load(), MilitaryUpgrades.load(), StrategicUpgrades.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<InfrastructureUpgrades>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends InfrastructureUpgrades> [] groups = new MetaGroup[] {StrategicUpgrades.METAGROUP, IndustrialUpgrades.METAGROUP, MilitaryUpgrades.METAGROUP };

        @Override
        public String getName() {
            return "InfrastructureUpgrades";
        }

        public Collection<MetaGroup<? extends InfrastructureUpgrades>> groups() {
            return Arrays.asList(groups);
        }
    }
}
