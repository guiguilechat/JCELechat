package fr.guiguilechat.eveonline.model.sde.items.types.module;

import fr.guiguilechat.eveonline.model.sde.items.types.Module;

public class NavigationComputer
    extends Module
{

    @Override
    public int getGroupId() {
        return  638;
    }

    @Override
    public Class<?> getGroup() {
        return NavigationComputer.class;
    }
}
