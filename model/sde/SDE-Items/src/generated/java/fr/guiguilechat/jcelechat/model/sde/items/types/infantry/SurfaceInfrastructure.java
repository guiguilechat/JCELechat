package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class SurfaceInfrastructure
    extends Infantry
{
    public final static SurfaceInfrastructure.MetaGroup METAGROUP = new SurfaceInfrastructure.MetaGroup();

    @Override
    public IMetaGroup<SurfaceInfrastructure> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SurfaceInfrastructure>
    {
        public final static String RESOURCE_PATH = "SDE/items/infantry/SurfaceInfrastructure.yaml";
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
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(SurfaceInfrastructure.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SurfaceInfrastructure> items;
        }
    }
}
