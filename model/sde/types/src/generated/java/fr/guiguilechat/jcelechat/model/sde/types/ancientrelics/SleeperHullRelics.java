package fr.guiguilechat.jcelechat.model.sde.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustryBlueprintRank;
import fr.guiguilechat.jcelechat.model.sde.types.AncientRelics;

public class SleeperHullRelics
    extends AncientRelics
{
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {IndustryBlueprintRank.INSTANCE })));
    public static final SleeperHullRelics.MetaGroup METAGROUP = new SleeperHullRelics.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<SleeperHullRelics> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SleeperHullRelics>
    {
        public static final String RESOURCE_PATH = "SDE/types/ancientrelics/SleeperHullRelics.yaml";
        private Map<Integer, SleeperHullRelics> cache = (null);

        @Override
        public IMetaCategory<? super SleeperHullRelics> category() {
            return AncientRelics.METACAT;
        }

        @Override
        public int getGroupId() {
            return  997;
        }

        @Override
        public String getName() {
            return "SleeperHullRelics";
        }

        @Override
        public synchronized Map<Integer, SleeperHullRelics> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SleeperHullRelics.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, SleeperHullRelics> types;
        }
    }
}
