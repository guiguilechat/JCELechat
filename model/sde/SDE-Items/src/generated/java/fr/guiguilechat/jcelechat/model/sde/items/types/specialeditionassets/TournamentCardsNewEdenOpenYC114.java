package fr.guiguilechat.jcelechat.model.sde.items.types.specialeditionassets;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.SpecialEditionAssets;
import org.yaml.snakeyaml.Yaml;

public class TournamentCardsNewEdenOpenYC114
    extends SpecialEditionAssets
{
    public static final TournamentCardsNewEdenOpenYC114 .MetaGroup METAGROUP = new TournamentCardsNewEdenOpenYC114 .MetaGroup();

    @Override
    public IMetaGroup<TournamentCardsNewEdenOpenYC114> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TournamentCardsNewEdenOpenYC114>
    {
        public static final String RESOURCE_PATH = "SDE/items/specialeditionassets/TournamentCardsNewEdenOpenYC114.yaml";
        private Map<String, TournamentCardsNewEdenOpenYC114> cache = (null);

        @Override
        public IMetaCategory<? super TournamentCardsNewEdenOpenYC114> category() {
            return SpecialEditionAssets.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1195;
        }

        @Override
        public String getName() {
            return "TournamentCardsNewEdenOpenYC114";
        }

        @Override
        public synchronized Map<String, TournamentCardsNewEdenOpenYC114> load() {
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
    }
}
