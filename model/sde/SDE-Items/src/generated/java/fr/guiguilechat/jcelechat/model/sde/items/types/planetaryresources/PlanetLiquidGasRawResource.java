package fr.guiguilechat.jcelechat.model.sde.items.types.planetaryresources;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryResources;
import org.yaml.snakeyaml.Yaml;

public class PlanetLiquidGasRawResource
    extends PlanetaryResources
{
    public final static PlanetLiquidGasRawResource.MetaGroup METAGROUP = new PlanetLiquidGasRawResource.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/planetaryresources/PlanetLiquidGasRawResource.yaml";
    private static Map<String, PlanetLiquidGasRawResource> cache = (null);

    @Override
    public int getGroupId() {
        return  1033;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<PlanetLiquidGasRawResource> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, PlanetLiquidGasRawResource> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PlanetLiquidGasRawResource.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, PlanetLiquidGasRawResource> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<PlanetLiquidGasRawResource>
    {

        @Override
        public MetaCategory<? super PlanetLiquidGasRawResource> category() {
            return PlanetaryResources.METACAT;
        }

        @Override
        public String getName() {
            return "PlanetLiquidGasRawResource";
        }

        @Override
        public Collection<PlanetLiquidGasRawResource> items() {
            return (load().values());
        }
    }
}
