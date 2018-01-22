package fr.guiguilechat.eveonline.model.sde.items.types.module;

import fr.guiguilechat.eveonline.model.sde.items.types.Module;

public class AntiBallisticDefenseSystem
    extends Module
{

    @Override
    public int getGroupId() {
        return  518;
    }

    @Override
    public Class<?> getGroup() {
        return AntiBallisticDefenseSystem.class;
    }
}
