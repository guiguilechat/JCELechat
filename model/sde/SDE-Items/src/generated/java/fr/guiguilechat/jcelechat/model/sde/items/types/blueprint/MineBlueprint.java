package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;

public class MineBlueprint
    extends Blueprint
{

    @Override
    public int getGroupId() {
        return  174;
    }

    @Override
    public Class<?> getGroup() {
        return MineBlueprint.class;
    }
}
