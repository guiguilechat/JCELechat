package fr.guiguilechat.jcelechat.model.sde.items.types.trading;

import fr.guiguilechat.jcelechat.model.sde.items.types.Trading;

public class TradeSession
    extends Trading
{

    @Override
    public int getGroupId() {
        return  95;
    }

    @Override
    public Class<?> getGroup() {
        return TradeSession.class;
    }
}
