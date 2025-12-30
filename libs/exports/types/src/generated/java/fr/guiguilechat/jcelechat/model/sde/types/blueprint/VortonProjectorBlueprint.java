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

public class VortonProjectorBlueprint
    extends Blueprint
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final VortonProjectorBlueprint.MetaGroup METAGROUP = new VortonProjectorBlueprint.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<VortonProjectorBlueprint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<VortonProjectorBlueprint>
    {
        public static final String RESOURCE_PATH = "SDE/types/blueprint/VortonProjectorBlueprint.yaml";
        private Map<Integer, VortonProjectorBlueprint> cache = (null);

        @Override
        public IMetaCategory<? super VortonProjectorBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4064;
        }

        @Override
        public String getName() {
            return "VortonProjectorBlueprint";
        }

        @Override
        public synchronized Map<Integer, VortonProjectorBlueprint> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(VortonProjectorBlueprint.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, VortonProjectorBlueprint> types;
        }
    }
}
