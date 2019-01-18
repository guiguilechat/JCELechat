package fr.guiguilechat.jcelechat.model.sde.items.types.lights;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Lights;
import org.yaml.snakeyaml.Yaml;

public class PointLights
    extends Lights
{
    public static final PointLights.MetaGroup METAGROUP = new PointLights.MetaGroup();

    @Override
    public IMetaGroup<PointLights> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PointLights>
    {
        public static final String RESOURCE_PATH = "SDE/items/lights/PointLights.yaml";
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
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(PointLights.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PointLights> items;
        }
    }
}
