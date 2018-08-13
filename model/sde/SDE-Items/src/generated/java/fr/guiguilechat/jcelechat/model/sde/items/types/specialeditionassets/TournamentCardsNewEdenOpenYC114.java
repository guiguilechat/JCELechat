package fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.SpecialEditionAssets;
import org.yaml.snakeyaml.Yaml;

public class TournamentCardsNewEdenOpenYC114
    extends SpecialEditionAssets
{
    public final static TournamentCardsNewEdenOpenYC114 .MetaGroup METAGROUP = new TournamentCardsNewEdenOpenYC114 .MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/specialeditionassets/TournamentCardsNewEdenOpenYC114.yaml";
    private static Map<String, TournamentCardsNewEdenOpenYC114> cache = (null);

    @Override
    public int getGroupId() {
        return  1195;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<TournamentCardsNewEdenOpenYC114> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, TournamentCardsNewEdenOpenYC114> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(TournamentCardsNewEdenOpenYC114 .class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, TournamentCardsNewEdenOpenYC114> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<TournamentCardsNewEdenOpenYC114>
    {

        @Override
        public MetaCategory<? super TournamentCardsNewEdenOpenYC114> category() {
            return SpecialEditionAssets.METACAT;
        }

        @Override
        public String getName() {
            return "TournamentCardsNewEdenOpenYC114";
        }

        @Override
        public Collection<TournamentCardsNewEdenOpenYC114> items() {
            return (load().values());
        }
    }
}
