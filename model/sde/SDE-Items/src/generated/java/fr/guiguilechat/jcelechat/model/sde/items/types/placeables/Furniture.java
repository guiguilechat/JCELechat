package fr.guiguilechat.jcelechat.model.sde.items.types.placeables;

import fr.guiguilechat.jcelechat.model.sde.items.types.Placeables;

public class Furniture
    extends Placeables
{

    @Override
    public int getGroupId() {
        return  940;
    }

    @Override
    public Class<?> getGroup() {
        return Furniture.class;
    }
}
