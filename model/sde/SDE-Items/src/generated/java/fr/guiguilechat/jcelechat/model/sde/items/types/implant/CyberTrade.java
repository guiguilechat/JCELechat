package fr.guiguilechat.jcelechat.model.sde.items.types.implant;

import fr.guiguilechat.jcelechat.model.sde.items.types.Implant;

public class CyberTrade
    extends Implant
{

    @Override
    public int getGroupId() {
        return  751;
    }

    @Override
    public Class<?> getGroup() {
        return CyberTrade.class;
    }
}
