package fr.guiguilechat.eveonline.model.sde.items.types.blueprint;

import fr.guiguilechat.eveonline.model.sde.items.types.Blueprint;

public class OreEnhancementBlueprints
    extends Blueprint
{

    @Override
    public int getGroupId() {
        return  889;
    }

    @Override
    public Class<?> getGroup() {
        return OreEnhancementBlueprints.class;
    }
}
