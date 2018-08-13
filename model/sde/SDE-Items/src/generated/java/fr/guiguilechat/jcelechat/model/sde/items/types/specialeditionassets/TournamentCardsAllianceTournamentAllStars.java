package fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.SpecialEditionAssets;
import org.yaml.snakeyaml.Yaml;

public class TournamentCardsAllianceTournamentAllStars
    extends SpecialEditionAssets
{
    public final static String RESOURCE_PATH = "SDE/items/specialeditionassets/TournamentCardsAllianceTournamentAllStars.yaml";
    private static Map<String, TournamentCardsAllianceTournamentAllStars> cache = (null);

    @Override
    public int getGroupId() {
        return  1225;
    }

    @Override
    public Class<?> getGroup() {
        return TournamentCardsAllianceTournamentAllStars.class;
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
}
