package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class OrbitalTarget
    extends Celestial
{
    public final static OrbitalTarget.MetaGroup METAGROUP = new OrbitalTarget.MetaGroup();

    @Override
    public IMetaGroup<OrbitalTarget> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<OrbitalTarget>
    {
        public final static String RESOURCE_PATH = "SDE/items/celestial/OrbitalTarget.yaml";
        private Map<String, OrbitalTarget> cache = (null);

        @Override
        public IMetaCategory<? super OrbitalTarget> category() {
            return Celestial.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1198;
        }

        @Override
        public String getName() {
            return "OrbitalTarget";
        }

        @Override
        public synchronized Map<String, OrbitalTarget> load() {
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
    }
}