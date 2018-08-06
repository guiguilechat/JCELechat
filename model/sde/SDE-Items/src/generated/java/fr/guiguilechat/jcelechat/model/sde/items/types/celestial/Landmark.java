package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Landmark
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  318;
    }

    @Override
    public Class<?> getGroup() {
        return Landmark.class;
    }
}
