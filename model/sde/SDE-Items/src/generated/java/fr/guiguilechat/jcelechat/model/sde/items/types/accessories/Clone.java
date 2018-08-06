package fr.guiguilechat.jcelechat.model.sde.items.types.accessories;

import fr.guiguilechat.jcelechat.model.sde.items.types.Accessories;

public class Clone
    extends Accessories
{

    @Override
    public int getGroupId() {
        return  23;
    }

    @Override
    public Class<?> getGroup() {
        return Clone.class;
    }
}
