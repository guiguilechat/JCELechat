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

public class SleeperEngineeringRelics
    extends AncientRelics
{
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {IndustryBlueprintRank.INSTANCE })));
    public static final SleeperEngineeringRelics.MetaGroup METAGROUP = new SleeperEngineeringRelics.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<SleeperEngineeringRelics> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SleeperEngineeringRelics>
    {
        public static final String RESOURCE_PATH = "SDE/types/ancientrelics/SleeperEngineeringRelics.yaml";
        private Map<Integer, SleeperEngineeringRelics> cache = (null);

        @Override
        public IMetaCategory<? super SleeperEngineeringRelics> category() {
            return AncientRelics.METACAT;
        }

        @Override
        public int getGroupId() {
            return  992;
        }

        @Override
        public String getName() {
            return "SleeperEngineeringRelics";
        }

        @Override
        public synchronized Map<Integer, SleeperEngineeringRelics> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SleeperEngineeringRelics.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, SleeperEngineeringRelics> types;
        }
    }
}
