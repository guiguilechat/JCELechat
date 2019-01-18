package fr.guiguilechat.jcelechat.model.sde.items.types.planetaryresources;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryResources;
import org.yaml.snakeyaml.Yaml;

public class PlanetLiquidGasRawResource
    extends PlanetaryResources
{
    public static final PlanetLiquidGasRawResource.MetaGroup METAGROUP = new PlanetLiquidGasRawResource.MetaGroup();

    @Override
    public IMetaGroup<PlanetLiquidGasRawResource> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PlanetLiquidGasRawResource>
    {
        public static final String RESOURCE_PATH = "SDE/items/planetaryresources/PlanetLiquidGasRawResource.yaml";
        private Map<String, PlanetLiquidGasRawResource> cache = (null);

        @Override
        public IMetaCategory<? super PlanetLiquidGasRawResource> category() {
            return PlanetaryResources.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1033;
        }

        @Override
        public String getName() {
            return "PlanetLiquidGasRawResource";
        }

        @Override
        public synchronized Map<String, PlanetLiquidGasRawResource> load() {
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
    }
}
