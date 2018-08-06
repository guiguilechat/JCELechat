package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;

public class OrbitalAssaultUnit
    extends Charge
{

    @Override
    public int getGroupId() {
        return  425;
    }

    @Override
    public Class<?> getGroup() {
        return OrbitalAssaultUnit.class;
    }
}
