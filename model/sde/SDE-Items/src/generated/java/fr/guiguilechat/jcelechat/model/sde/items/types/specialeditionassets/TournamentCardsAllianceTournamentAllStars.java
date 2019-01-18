package fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.SpecialEditionAssets;
import org.yaml.snakeyaml.Yaml;

public class TournamentCardsAllianceTournamentAllStars
    extends SpecialEditionAssets
{
    public static final TournamentCardsAllianceTournamentAllStars.MetaGroup METAGROUP = new TournamentCardsAllianceTournamentAllStars.MetaGroup();

    @Override
    public IMetaGroup<TournamentCardsAllianceTournamentAllStars> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TournamentCardsAllianceTournamentAllStars>
    {
        public static final String RESOURCE_PATH = "SDE/items/specialeditionassets/TournamentCardsAllianceTournamentAllStars.yaml";
        private Map<String, TournamentCardsAllianceTournamentAllStars> cache = (null);

        @Override
        public IMetaCategory<? super TournamentCardsAllianceTournamentAllStars> category() {
            return SpecialEditionAssets.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1225;
        }

        @Override
        public String getName() {
            return "TournamentCardsAllianceTournamentAllStars";
        }

        @Override
        public synchronized Map<String, TournamentCardsAllianceTournamentAllStars> load() {
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
}
