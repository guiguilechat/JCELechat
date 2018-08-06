package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import fr.guiguilechat.jcelechat.model.sde.items.types.Material;

public class Money
    extends Material
{

    @Override
    public int getGroupId() {
        return  17;
    }

    @Override
    public Class<?> getGroup() {
        return Money.class;
    }
}
