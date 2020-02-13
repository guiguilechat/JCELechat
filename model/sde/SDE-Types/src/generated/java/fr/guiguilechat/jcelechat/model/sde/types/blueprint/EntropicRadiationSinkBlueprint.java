package fr.guiguilechat.jcelechat.model.sde.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class EntropicRadiationSinkBlueprint
    extends Blueprint
{
    public static final EntropicRadiationSinkBlueprint.MetaGroup METAGROUP = new EntropicRadiationSinkBlueprint.MetaGroup();

    @Override
    public IMetaGroup<EntropicRadiationSinkBlueprint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EntropicRadiationSinkBlueprint>
    {
        public static final String RESOURCE_PATH = "SDE/items/blueprint/EntropicRadiationSinkBlueprint.yaml";
        private Map<String, EntropicRadiationSinkBlueprint> cache = (null);

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
        public synchronized Map<String, EntropicRadiationSinkBlueprint> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(EntropicRadiationSinkBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, EntropicRadiationSinkBlueprint> items;
        }
    }
}
