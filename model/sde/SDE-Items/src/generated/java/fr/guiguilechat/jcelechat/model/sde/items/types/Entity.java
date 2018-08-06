package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.entity.IrregularBattleship;
import fr.guiguilechat.jcelechat.model.sde.items.types.entity.IrregularCruiser;
import fr.guiguilechat.jcelechat.model.sde.items.types.entity.IrregularDreadnought;
import fr.guiguilechat.jcelechat.model.sde.items.types.entity.IrregularFrigate;
import fr.guiguilechat.jcelechat.model.sde.items.types.entity.MissionContainer;
import fr.guiguilechat.jcelechat.model.sde.items.types.entity.SpawnContainer;

public abstract class Entity
    extends Item
{

    @Override
    public int getCategoryId() {
        return  11;
    }

    @Override
    public Class<?> getCategory() {
        return Entity.class;
    }

    public static Map<String, ? extends Entity> loadCategory() {
        return Stream.of(IrregularBattleship.load(), IrregularCruiser.load(), IrregularDreadnought.load(), IrregularFrigate.load(), MissionContainer.load(), SpawnContainer.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
