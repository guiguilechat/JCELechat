package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class NPCForceAuxiliary
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1879;
    }

    @Override
    public Class<?> getGroup() {
        return NPCForceAuxiliary.class;
    }
}
