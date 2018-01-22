package fr.guiguilechat.eveonline.model.sde.items.types.module;

import fr.guiguilechat.eveonline.model.sde.items.types.Module;

public class CloakEnhancements
    extends Module
{

    @Override
    public int getGroupId() {
        return  878;
    }

    @Override
    public Class<?> getGroup() {
        return CloakEnhancements.class;
    }
}
