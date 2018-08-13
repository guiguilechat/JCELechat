package fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.SpecialEditionAssets;
import org.yaml.snakeyaml.Yaml;

public class TournamentCardsAllianceTournamentAllStars
    extends SpecialEditionAssets
{
    public final static TournamentCardsAllianceTournamentAllStars.MetaGroup METAGROUP = new TournamentCardsAllianceTournamentAllStars.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/specialeditionassets/TournamentCardsAllianceTournamentAllStars.yaml";
    private static Map<String, TournamentCardsAllianceTournamentAllStars> cache = (null);

    @Override
    public int getGroupId() {
        return  1225;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<TournamentCardsAllianceTournamentAllStars> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, TournamentCardsAllianceTournamentAllStars> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(TournamentCardsAllianceTournamentAllStars.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, TournamentCardsAllianceTournamentAllStars> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<TournamentCardsAllianceTournamentAllStars>
    {

        @Override
        public MetaCategory<? super TournamentCardsAllianceTournamentAllStars> category() {
            return SpecialEditionAssets.METACAT;
        }

        @Override
        public String getName() {
            return "TournamentCardsAllianceTournamentAllStars";
        }

        @Override
        public Collection<TournamentCardsAllianceTournamentAllStars> items() {
            return (load().values());
        }
    }
}
