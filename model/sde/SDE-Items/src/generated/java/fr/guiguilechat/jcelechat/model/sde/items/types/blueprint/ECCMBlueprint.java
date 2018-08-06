package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;

public class ECCMBlueprint
    extends Blueprint
{

    @Override
    public int getGroupId() {
        return  131;
    }

    @Override
    public Class<?> getGroup() {
        return ECCMBlueprint.class;
    }
}
