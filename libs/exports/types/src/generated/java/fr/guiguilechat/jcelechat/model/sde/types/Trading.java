package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.trading.TradeSession;

public abstract class Trading
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Trading.MetaCat METACAT = new Trading.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

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
            return Arrays.asList(fr.guiguilechat.jcelechat.model.sde.types.trading.Trading.METAGROUP, TradeSession.METAGROUP);
        }
    }
}
