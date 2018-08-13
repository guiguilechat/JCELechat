package fr.guiguilechat.jcelechat.model.sde.items.types.planetaryresources;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryResources;
import org.yaml.snakeyaml.Yaml;

public class PlanetOrganicRawResource
    extends PlanetaryResources
{
    public final static PlanetOrganicRawResource.MetaGroup METAGROUP = new PlanetOrganicRawResource.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/planetaryresources/PlanetOrganicRawResource.yaml";
    private static Map<String, PlanetOrganicRawResource> cache = (null);

    @Override
    public int getGroupId() {
        return  1035;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<PlanetOrganicRawResource> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, PlanetOrganicRawResource> load() {
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<PlanetOrganicRawResource>
    {

        @Override
        public MetaCategory<? super PlanetOrganicRawResource> category() {
            return PlanetaryResources.METACAT;
        }

        @Override
        public String getName() {
            return "PlanetOrganicRawResource";
        }

        @Override
        public Collection<PlanetOrganicRawResource> items() {
            return (load().values());
        }
    }
}
