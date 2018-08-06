package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class IrregularBattlecruiser
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1666;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularBattlecruiser.class;
    }
}
