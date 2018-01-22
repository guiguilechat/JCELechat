package fr.guiguilechat.eveonline.model.sde.items.types.blueprint;

import fr.guiguilechat.eveonline.model.sde.items.types.Blueprint;

public class ProximityDroneBlueprint
    extends Blueprint
{

    @Override
    public int getGroupId() {
        return  175;
    }

    @Override
    public Class<?> getGroup() {
        return ProximityDroneBlueprint.class;
    }
}
