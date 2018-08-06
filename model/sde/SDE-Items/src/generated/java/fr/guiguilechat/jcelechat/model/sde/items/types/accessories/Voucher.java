package fr.guiguilechat.jcelechat.model.sde.items.types.accessories;

import fr.guiguilechat.jcelechat.model.sde.items.types.Accessories;

public class Voucher
    extends Accessories
{

    @Override
    public int getGroupId() {
        return  24;
    }

    @Override
    public Class<?> getGroup() {
        return Voucher.class;
    }
}
