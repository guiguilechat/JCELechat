package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;

public class IceCompressionBlueprints
    extends Blueprint
{

    @Override
    public int getGroupId() {
        return  890;
    }

    @Override
    public Class<?> getGroup() {
        return IceCompressionBlueprints.class;
    }
}
