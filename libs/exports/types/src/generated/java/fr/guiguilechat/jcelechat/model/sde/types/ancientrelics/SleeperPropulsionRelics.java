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

public class SleeperPropulsionRelics
    extends AncientRelics
{
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {IndustryBlueprintRank.INSTANCE })));
    public static final SleeperPropulsionRelics.MetaGroup METAGROUP = new SleeperPropulsionRelics.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<SleeperPropulsionRelics> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SleeperPropulsionRelics>
    {
        public static final String RESOURCE_PATH = "SDE/types/ancientrelics/SleeperPropulsionRelics.yaml";
        private Map<Integer, SleeperPropulsionRelics> cache = (null);

        @Override
        public IMetaCategory<? super SleeperPropulsionRelics> category() {
            return AncientRelics.METACAT;
        }

        @Override
        public int getGroupId() {
            return  971;
        }

        @Override
        public String getName() {
            return "SleeperPropulsionRelics";
        }

        @Override
        public synchronized Map<Integer, SleeperPropulsionRelics> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SleeperPropulsionRelics.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, SleeperPropulsionRelics> types;
        }
    }
}
