package fr.guiguilechat.jcelechat.model.sde.items.types.asteroid;

import fr.guiguilechat.jcelechat.model.sde.items.types.Asteroid;

public class AncientCompressedIce
    extends Asteroid
{

    @Override
    public int getGroupId() {
        return  903;
    }

    @Override
    public Class<?> getGroup() {
        return AncientCompressedIce.class;
    }
}
