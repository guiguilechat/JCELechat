package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;

public class CompactXLTorpedo
    extends Charge
{

    @Override
    public int getGroupId() {
        return  1010;
    }

    @Override
    public Class<?> getGroup() {
        return CompactXLTorpedo.class;
    }
}
