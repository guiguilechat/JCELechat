package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.specialeditionassets.FestivalChargesExpired;
import fr.guiguilechat.jcelechat.model.sde.types.specialeditionassets.SpecialEditionCommodities;
import fr.guiguilechat.jcelechat.model.sde.types.specialeditionassets.TournamentCardsAllianceTournamentAllStars;
import fr.guiguilechat.jcelechat.model.sde.types.specialeditionassets.TournamentCardsNewEdenOpenYC114;

public abstract class SpecialEditionAssets
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final SpecialEditionAssets.MetaCat METACAT = new SpecialEditionAssets.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

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
