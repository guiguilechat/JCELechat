package fr.guiguilechat.jcelechat.model.sde.types.specialeditionassets;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.SpecialEditionAssets;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class TournamentCardsAllianceTournamentAllStars
    extends SpecialEditionAssets
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final TournamentCardsAllianceTournamentAllStars.MetaGroup METAGROUP = new TournamentCardsAllianceTournamentAllStars.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<TournamentCardsAllianceTournamentAllStars> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TournamentCardsAllianceTournamentAllStars>
    {
        public static final String RESOURCE_PATH = "SDE/types/specialeditionassets/TournamentCardsAllianceTournamentAllStars.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(TournamentCardsAllianceTournamentAllStars.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, TournamentCardsAllianceTournamentAllStars> types;
        }
    }
}
