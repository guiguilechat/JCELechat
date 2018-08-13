package fr.guiguilechat.jcelechat.model.sde.items.types.orbitals;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Orbitals;
import org.yaml.snakeyaml.Yaml;

public class OrbitalConstructionPlatform
    extends Orbitals
{
    public final static OrbitalConstructionPlatform.MetaGroup METAGROUP = new OrbitalConstructionPlatform.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/orbitals/OrbitalConstructionPlatform.yaml";
    private static Map<String, OrbitalConstructionPlatform> cache = (null);

    @Override
    public int getGroupId() {
        return  1106;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<OrbitalConstructionPlatform> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, OrbitalConstructionPlatform> load() {
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<OrbitalConstructionPlatform>
    {

        @Override
        public MetaCategory<? super OrbitalConstructionPlatform> category() {
            return Orbitals.METACAT;
        }

        @Override
        public String getName() {
            return "OrbitalConstructionPlatform";
        }

        @Override
        public Collection<OrbitalConstructionPlatform> items() {
            return (load().values());
        }
    }
}
