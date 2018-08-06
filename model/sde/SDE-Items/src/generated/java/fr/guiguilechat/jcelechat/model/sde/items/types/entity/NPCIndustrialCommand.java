package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class NPCIndustrialCommand
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1896;
    }

    @Override
    public Class<?> getGroup() {
        return NPCIndustrialCommand.class;
    }
}
