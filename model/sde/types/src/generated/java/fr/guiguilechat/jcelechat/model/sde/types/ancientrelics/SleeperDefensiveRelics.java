package fr.guiguilechat.jcelechat.model.sde.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustryBlueprintRank;
import fr.guiguilechat.jcelechat.model.sde.types.AncientRelics;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class SleeperDefensiveRelics
    extends AncientRelics
{
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {IndustryBlueprintRank.INSTANCE })));
    public static final SleeperDefensiveRelics.MetaGroup METAGROUP = new SleeperDefensiveRelics.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<SleeperDefensiveRelics> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SleeperDefensiveRelics>
    {
        public static final String RESOURCE_PATH = "SDE/types/ancientrelics/SleeperDefensiveRelics.yaml";
        private Map<Integer, SleeperDefensiveRelics> cache = (null);

        @Override
        public IMetaCategory<? super SleeperDefensiveRelics> category() {
            return AncientRelics.METACAT;
        }

        @Override
        public int getGroupId() {
            return  993;
        }

        @Override
        public String getName() {
            return "SleeperDefensiveRelics";
        }

        @Override
        public synchronized Map<Integer, SleeperDefensiveRelics> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SleeperDefensiveRelics.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, SleeperDefensiveRelics> types;
        }
    }
}
