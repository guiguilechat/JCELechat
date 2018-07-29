package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class IrregularIndustrialCommandShip
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1925;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularIndustrialCommandShip.class;
    }
}
