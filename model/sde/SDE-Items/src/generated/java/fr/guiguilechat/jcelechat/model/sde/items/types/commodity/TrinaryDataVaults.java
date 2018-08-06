package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;

public class TrinaryDataVaults
    extends Commodity
{

    @Override
    public int getGroupId() {
        return  1977;
    }

    @Override
    public Class<?> getGroup() {
        return TrinaryDataVaults.class;
    }
}
