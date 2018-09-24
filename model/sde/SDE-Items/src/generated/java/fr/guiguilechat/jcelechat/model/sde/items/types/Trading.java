package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.trading.TradeSession;

public abstract class Trading
    extends Item
{
    public final static Trading.MetaCat METACAT = new Trading.MetaCat();

    @Override
    public IMetaCategory<Trading> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Trading>
    {

        @Override
        public int getCategoryId() {
            return  10;
        }

        @Override
        public String getName() {
            return "Trading";
        }

        @Override
        public Collection<IMetaGroup<? extends Trading>> groups() {
            return Arrays.asList(fr.guiguilechat.jcelechat.model.sde.items.types.trading.Trading.METAGROUP, TradeSession.METAGROUP);
        }
    }
}
