package fr.guiguilechat.eveonline.model.sde.items.types.module;

import fr.guiguilechat.eveonline.model.sde.items.types.Module;

public class DroneBayExpander
    extends Module
{

    @Override
    public int getGroupId() {
        return  357;
    }

    @Override
    public Class<?> getGroup() {
        return DroneBayExpander.class;
    }
}
