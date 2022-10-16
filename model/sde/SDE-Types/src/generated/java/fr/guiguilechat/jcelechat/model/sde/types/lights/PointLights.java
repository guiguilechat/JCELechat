package fr.guiguilechat.jcelechat.model.sde.types.lights;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Lights;
import org.yaml.snakeyaml.Yaml;

public class PointLights
    extends Lights
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final PointLights.MetaGroup METAGROUP = new PointLights.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<PointLights> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PointLights>
    {
        public static final String RESOURCE_PATH = "SDE/types/lights/PointLights.yaml";
        private Map<String, PointLights> cache = (null);

        @Override
        public IMetaCategory<? super PointLights> category() {
            return Lights.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1110;
        }

        @Override
        public String getName() {
            return "PointLights";
        }

        @Override
        public synchronized Map<String, PointLights> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PointLights.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PointLights> types;
        }
    }
}
