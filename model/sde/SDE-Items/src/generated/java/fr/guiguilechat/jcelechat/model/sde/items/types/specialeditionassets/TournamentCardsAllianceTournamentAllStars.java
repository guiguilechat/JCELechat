package fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.SpecialEditionAssets;

public class TournamentCardsAllianceTournamentAllStars
    extends SpecialEditionAssets
{
    public final static String RESOURCE_PATH = "SDE/items/specialeditionassets/TournamentCardsAllianceTournamentAllStars.yaml";
    private static LinkedHashMap<String, TournamentCardsAllianceTournamentAllStars> cache = (null);

    @Override
    public int getGroupId() {
        return  1225;
    }

    @Override
    public Class<?> getGroup() {
        return TournamentCardsAllianceTournamentAllStars.class;
    }

    public static synchronized LinkedHashMap<String, TournamentCardsAllianceTournamentAllStars> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(TournamentCardsAllianceTournamentAllStars.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, TournamentCardsAllianceTournamentAllStars> items;
    }
}
