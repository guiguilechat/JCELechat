package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets.FestivalChargesExpired;
import fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets.SpecialEditionCommodities;
import fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets.TournamentCardsAllianceTournamentAllStars;
import fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets.TournamentCardsNewEdenOpenYC114;

public abstract class SpecialEditionAssets
    extends Item
{
    public final static SpecialEditionAssets.MetaCat METACAT = new SpecialEditionAssets.MetaCat();

    @Override
    public IMetaCategory<SpecialEditionAssets> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<SpecialEditionAssets>
    {

        @Override
        public int getCategoryId() {
            return  63;
        }

        @Override
        public String getName() {
            return "SpecialEditionAssets";
        }

        @Override
        public Collection<IMetaGroup<? extends SpecialEditionAssets>> groups() {
            return Arrays.asList(FestivalChargesExpired.METAGROUP, SpecialEditionCommodities.METAGROUP, TournamentCardsNewEdenOpenYC114 .METAGROUP, TournamentCardsAllianceTournamentAllStars.METAGROUP);
        }
    }
}
