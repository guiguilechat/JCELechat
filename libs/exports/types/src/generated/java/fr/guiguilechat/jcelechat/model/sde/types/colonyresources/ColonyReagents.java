package fr.guiguilechat.jcelechat.model.sde.types.colonyresources;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.ColonyResources;

public class ColonyReagents
    extends ColonyResources
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final ColonyReagents.MetaGroup METAGROUP = new ColonyReagents.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<ColonyReagents> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ColonyReagents>
    {
        public static final String RESOURCE_PATH = "SDE/types/colonyresources/ColonyReagents.yaml";
        private Map<Integer, ColonyReagents> cache = (null);

        @Override
        public IMetaCategory<? super ColonyReagents> category() {
            return ColonyResources.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4729;
        }

        @Override
        public String getName() {
            return "ColonyReagents";
        }

        @Override
        public synchronized Map<Integer, ColonyReagents> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ColonyReagents.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, ColonyReagents> types;
        }
    }
}
