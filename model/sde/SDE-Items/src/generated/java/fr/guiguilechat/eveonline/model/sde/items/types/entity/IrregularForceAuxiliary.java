package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import fr.guiguilechat.eveonline.model.sde.items.types.Entity;

public class IrregularForceAuxiliary
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1725;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularForceAuxiliary.class;
    }
}
