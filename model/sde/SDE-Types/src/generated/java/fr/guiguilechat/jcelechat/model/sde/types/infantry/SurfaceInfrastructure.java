package fr.guiguilechat.jcelechat.model.sde.types.infantry;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class SurfaceInfrastructure
    extends Infantry
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final SurfaceInfrastructure.MetaGroup METAGROUP = new SurfaceInfrastructure.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<SurfaceInfrastructure> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SurfaceInfrastructure>
    {
        public static final String RESOURCE_PATH = "SDE/types/infantry/SurfaceInfrastructure.yaml";
        private Map<String, SurfaceInfrastructure> cache = (null);

        @Override
        public IMetaCategory<? super SurfaceInfrastructure> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  364204;
        }

        @Override
        public String getName() {
            return "SurfaceInfrastructure";
        }

        @Override
        public synchronized Map<String, SurfaceInfrastructure> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SurfaceInfrastructure.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SurfaceInfrastructure> types;
        }
    }
}
