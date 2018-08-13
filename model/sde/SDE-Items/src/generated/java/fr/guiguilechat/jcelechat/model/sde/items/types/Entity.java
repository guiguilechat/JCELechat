package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.entity.IrregularBattleship;
import fr.guiguilechat.jcelechat.model.sde.items.types.entity.IrregularCruiser;
import fr.guiguilechat.jcelechat.model.sde.items.types.entity.IrregularDreadnought;
import fr.guiguilechat.jcelechat.model.sde.items.types.entity.IrregularFrigate;
import fr.guiguilechat.jcelechat.model.sde.items.types.entity.MissionContainer;
import fr.guiguilechat.jcelechat.model.sde.items.types.entity.SpawnContainer;

public abstract class Entity
    extends Item
{
    public final static Entity.MetaCat METACAT = new Entity.MetaCat();

    @Override
    public int getCategoryId() {
        return  11;
    }

    @Override
    public MetaCategory<Entity> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Entity> loadCategory() {
        return Stream.of(IrregularBattleship.load(), IrregularCruiser.load(), IrregularDreadnought.load(), IrregularFrigate.load(), MissionContainer.load(), SpawnContainer.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<Entity>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Entity> [] groups = new MetaGroup[] {SpawnContainer.METAGROUP, MissionContainer.METAGROUP, IrregularFrigate.METAGROUP, IrregularCruiser.METAGROUP, IrregularBattleship.METAGROUP, IrregularDreadnought.METAGROUP };

        @Override
        public String getName() {
            return "Entity";
        }

        public Collection<MetaGroup<? extends Entity>> groups() {
            return Arrays.asList(groups);
        }
    }
}
