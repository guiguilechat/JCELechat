package fr.guiguilechat.jcelechat.model.sde.items.types.planetaryresources;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryResources;
import org.yaml.snakeyaml.Yaml;

public class PlanetOrganicRawResource
    extends PlanetaryResources
{
    public final static PlanetOrganicRawResource.MetaGroup METAGROUP = new PlanetOrganicRawResource.MetaGroup();

    @Override
    public IMetaGroup<PlanetOrganicRawResource> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PlanetOrganicRawResource>
    {
        public final static String RESOURCE_PATH = "SDE/items/planetaryresources/PlanetOrganicRawResource.yaml";
        private Map<String, PlanetOrganicRawResource> cache = (null);

        @Override
        public IMetaCategory<? super PlanetOrganicRawResource> category() {
            return PlanetaryResources.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1035;
        }

        @Override
        public String getName() {
            return "PlanetOrganicRawResource";
        }

        @Override
        public synchronized Map<String, PlanetOrganicRawResource> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(PlanetOrganicRawResource.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PlanetOrganicRawResource> items;
        }
    }
}
