package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;

public class ObsoleteProbes
    extends Charge
{

    @Override
    public int getGroupId() {
        return  972;
    }

    @Override
    public Class<?> getGroup() {
        return ObsoleteProbes.class;
    }
}
