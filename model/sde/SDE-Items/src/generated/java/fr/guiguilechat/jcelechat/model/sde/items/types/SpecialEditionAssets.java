package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets.FestivalChargesExpired;
import fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets.SpecialEditionCommodities;
import fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets.TournamentCardsAllianceTournamentAllStars;
import fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets.TournamentCardsNewEdenOpenYC114;

public abstract class SpecialEditionAssets
    extends Item
{

    @Override
    public int getCategoryId() {
        return  63;
    }

    @Override
    public Class<?> getCategory() {
        return SpecialEditionAssets.class;
    }

    public static Map<String, ? extends SpecialEditionAssets> loadCategory() {
        return Stream.of(FestivalChargesExpired.load(), SpecialEditionCommodities.load(), TournamentCardsAllianceTournamentAllStars.load(), TournamentCardsNewEdenOpenYC114 .load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
