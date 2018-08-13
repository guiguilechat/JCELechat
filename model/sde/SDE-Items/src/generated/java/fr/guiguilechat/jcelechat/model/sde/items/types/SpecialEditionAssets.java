package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets.FestivalChargesExpired;
import fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets.SpecialEditionCommodities;
import fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets.TournamentCardsAllianceTournamentAllStars;
import fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets.TournamentCardsNewEdenOpenYC114;

public abstract class SpecialEditionAssets
    extends Item
{
    public final static SpecialEditionAssets.MetaCat METACAT = new SpecialEditionAssets.MetaCat();

    @Override
    public int getCategoryId() {
        return  63;
    }

    @Override
    public MetaCategory<SpecialEditionAssets> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends SpecialEditionAssets> loadCategory() {
        return Stream.of(FestivalChargesExpired.load(), SpecialEditionCommodities.load(), TournamentCardsAllianceTournamentAllStars.load(), TournamentCardsNewEdenOpenYC114 .load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<SpecialEditionAssets>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends SpecialEditionAssets> [] groups = new MetaGroup[] {FestivalChargesExpired.METAGROUP, SpecialEditionCommodities.METAGROUP, TournamentCardsNewEdenOpenYC114 .METAGROUP, TournamentCardsAllianceTournamentAllStars.METAGROUP };

        @Override
        public String getName() {
            return "SpecialEditionAssets";
        }

        public Collection<MetaGroup<? extends SpecialEditionAssets>> groups() {
            return Arrays.asList(groups);
        }
    }
}
