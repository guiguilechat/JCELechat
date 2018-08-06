package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class InfantryModules
    extends Infantry
{

    @Override
    public int getGroupId() {
        return  351121;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryModules.class;
    }
}
