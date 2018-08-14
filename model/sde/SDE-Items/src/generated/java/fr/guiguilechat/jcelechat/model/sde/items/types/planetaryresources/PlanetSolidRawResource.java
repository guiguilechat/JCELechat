package fr.guiguilechat.jcelechat.model.sde.items.types.planetaryresources;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryResources;
import org.yaml.snakeyaml.Yaml;

public class PlanetSolidRawResource
    extends PlanetaryResources
{
    public final static PlanetSolidRawResource.MetaGroup METAGROUP = new PlanetSolidRawResource.MetaGroup();

    @Override
    public IMetaGroup<PlanetSolidRawResource> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PlanetSolidRawResource>
    {
        public final static String RESOURCE_PATH = "SDE/items/planetaryresources/PlanetSolidRawResource.yaml";
        private Map<String, PlanetSolidRawResource> cache = (null);

        @Override
        public IMetaCategory<? super PlanetSolidRawResource> category() {
            return PlanetaryResources.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1032;
        }

        @Override
        public String getName() {
            return "PlanetSolidRawResource";
        }

        @Override
        public synchronized Map<String, PlanetSolidRawResource> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(PlanetSolidRawResource.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PlanetSolidRawResource> items;
        }
    }
}
