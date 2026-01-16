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

public class TournamentCardsNewEdenOpenYC114
    extends SpecialEditionAssets
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final TournamentCardsNewEdenOpenYC114 .MetaGroup METAGROUP = new TournamentCardsNewEdenOpenYC114 .MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<TournamentCardsNewEdenOpenYC114> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TournamentCardsNewEdenOpenYC114>
    {
        public static final String RESOURCE_PATH = "SDE/types/specialeditionassets/TournamentCardsNewEdenOpenYC114.yaml";
        private Map<Integer, TournamentCardsNewEdenOpenYC114> cache = (null);

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
        public synchronized Map<Integer, TournamentCardsNewEdenOpenYC114> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(TournamentCardsNewEdenOpenYC114 .MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, TournamentCardsNewEdenOpenYC114> types;
        }
    }
}
