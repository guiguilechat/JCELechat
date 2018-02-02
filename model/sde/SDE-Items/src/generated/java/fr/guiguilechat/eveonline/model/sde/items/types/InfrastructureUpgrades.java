package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.types.infrastructureupgrades.IndustrialUpgrades;
import fr.guiguilechat.eveonline.model.sde.items.types.infrastructureupgrades.MilitaryUpgrades;
import fr.guiguilechat.eveonline.model.sde.items.types.infrastructureupgrades.StrategicUpgrades;

public abstract class InfrastructureUpgrades
    extends Item
{

    @Override
    public int getCategoryId() {
        return  39;
    }

    @Override
    public Class<?> getCategory() {
        return InfrastructureUpgrades.class;
    }

    public static Map<String, ? extends InfrastructureUpgrades> loadCategory() {
        return Stream.of(StrategicUpgrades.load(), MilitaryUpgrades.load(), IndustrialUpgrades.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
