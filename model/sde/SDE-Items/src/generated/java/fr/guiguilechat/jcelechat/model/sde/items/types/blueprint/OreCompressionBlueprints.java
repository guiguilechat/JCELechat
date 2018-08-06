package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;

public class OreCompressionBlueprints
    extends Blueprint
{

    @Override
    public int getGroupId() {
        return  888;
    }

    @Override
    public Class<?> getGroup() {
        return OreCompressionBlueprints.class;
    }
}
