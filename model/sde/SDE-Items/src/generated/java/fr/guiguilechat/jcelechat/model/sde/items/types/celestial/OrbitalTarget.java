package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class OrbitalTarget
    extends Celestial
{
    public final static OrbitalTarget.MetaGroup METAGROUP = new OrbitalTarget.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/celestial/OrbitalTarget.yaml";
    private static Map<String, OrbitalTarget> cache = (null);

    @Override
    public int getGroupId() {
        return  1198;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<OrbitalTarget> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, OrbitalTarget> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(OrbitalTarget.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, OrbitalTarget> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<OrbitalTarget>
    {

        @Override
        public MetaCategory<? super OrbitalTarget> category() {
            return Celestial.METACAT;
        }

        @Override
        public String getName() {
            return "OrbitalTarget";
        }

        @Override
        public Collection<OrbitalTarget> items() {
            return (load().values());
        }
    }
}
