package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class ShipModifiers
    extends Module
{

    @Override
    public int getGroupId() {
        return  1306;
    }

    @Override
    public Class<?> getGroup() {
        return ShipModifiers.class;
    }
}
