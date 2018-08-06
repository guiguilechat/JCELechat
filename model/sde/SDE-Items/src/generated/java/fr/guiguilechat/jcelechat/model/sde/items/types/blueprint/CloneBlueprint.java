package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;

public class CloneBlueprint
    extends Blueprint
{

    @Override
    public int getGroupId() {
        return  104;
    }

    @Override
    public Class<?> getGroup() {
        return CloneBlueprint.class;
    }
}
