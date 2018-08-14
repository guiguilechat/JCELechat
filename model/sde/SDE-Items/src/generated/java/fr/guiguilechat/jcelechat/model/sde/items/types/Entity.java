package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
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
    public final static Entity.MetaCat METACAT = new Entity.MetaCat();

    @Override
    public IMetaCategory<Entity> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Entity>
    {

        @Override
        public int getCategoryId() {
            return  11;
        }

        @Override
        public String getName() {
            return "Entity";
        }

        @Override
        public Collection<IMetaGroup<? extends Entity>> groups() {
            return Arrays.asList(SpawnContainer.METAGROUP, MissionContainer.METAGROUP, IrregularFrigate.METAGROUP, IrregularCruiser.METAGROUP, IrregularBattleship.METAGROUP, IrregularDreadnought.METAGROUP);
        }
    }
}
