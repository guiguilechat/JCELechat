package fr.guiguilechat.jcelechat.model.sde.types.blueprint;

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
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;

public class EntropicRadiationSinkBlueprint
    extends Blueprint
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final EntropicRadiationSinkBlueprint.MetaGroup METAGROUP = new EntropicRadiationSinkBlueprint.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<EntropicRadiationSinkBlueprint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EntropicRadiationSinkBlueprint>
    {
        public static final String RESOURCE_PATH = "SDE/types/blueprint/EntropicRadiationSinkBlueprint.yaml";
        private Map<Integer, EntropicRadiationSinkBlueprint> cache = (null);

        @Override
        public IMetaCategory<? super EntropicRadiationSinkBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1992;
        }

        @Override
        public String getName() {
            return "EntropicRadiationSinkBlueprint";
        }

        @Override
        public synchronized Map<Integer, EntropicRadiationSinkBlueprint> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(EntropicRadiationSinkBlueprint.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, EntropicRadiationSinkBlueprint> types;
        }
    }
}
