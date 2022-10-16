package fr.guiguilechat.jcelechat.model.sde.types.planetaryresources;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExportTaxMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImportTaxMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryResources;
import org.yaml.snakeyaml.Yaml;

public class PlanetSolidRawResource
    extends PlanetaryResources
{
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Capacity.INSTANCE, ImportTaxMultiplier.INSTANCE, ExportTaxMultiplier.INSTANCE })));
    public static final PlanetSolidRawResource.MetaGroup METAGROUP = new PlanetSolidRawResource.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<PlanetSolidRawResource> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PlanetSolidRawResource>
    {
        public static final String RESOURCE_PATH = "SDE/types/planetaryresources/PlanetSolidRawResource.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(PlanetSolidRawResource.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PlanetSolidRawResource> types;
        }
    }
}
