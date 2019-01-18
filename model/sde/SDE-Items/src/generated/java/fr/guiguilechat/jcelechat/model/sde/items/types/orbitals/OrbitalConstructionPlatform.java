package fr.guiguilechat.jcelechat.model.sde.items.types.orbitals;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Orbitals;
import org.yaml.snakeyaml.Yaml;

public class OrbitalConstructionPlatform
    extends Orbitals
{
    public static final OrbitalConstructionPlatform.MetaGroup METAGROUP = new OrbitalConstructionPlatform.MetaGroup();

    @Override
    public IMetaGroup<OrbitalConstructionPlatform> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<OrbitalConstructionPlatform>
    {
        public static final String RESOURCE_PATH = "SDE/items/orbitals/OrbitalConstructionPlatform.yaml";
        private Map<String, OrbitalConstructionPlatform> cache = (null);

        @Override
        public IMetaCategory<? super OrbitalConstructionPlatform> category() {
            return Orbitals.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1106;
        }

        @Override
        public String getName() {
            return "OrbitalConstructionPlatform";
        }

        @Override
        public synchronized Map<String, OrbitalConstructionPlatform> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(OrbitalConstructionPlatform.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, OrbitalConstructionPlatform> items;
        }
    }
}
