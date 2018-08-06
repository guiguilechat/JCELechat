package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;

public class DepricatedSubsystems
    extends Commodity
{

    @Override
    public int getGroupId() {
        return  955;
    }

    @Override
    public Class<?> getGroup() {
        return DepricatedSubsystems.class;
    }
}
