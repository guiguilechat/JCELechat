package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class ForceField
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  411;
    }

    @Override
    public Class<?> getGroup() {
        return ForceField.class;
    }
}
