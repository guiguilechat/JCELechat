package fr.guiguilechat.jcelechat.model.sde.types.celestial;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class OrbitalTarget
    extends Celestial
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final OrbitalTarget.MetaGroup METAGROUP = new OrbitalTarget.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<OrbitalTarget> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<OrbitalTarget>
    {
        public static final String RESOURCE_PATH = "SDE/types/celestial/OrbitalTarget.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(OrbitalTarget.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, OrbitalTarget> types;
        }
    }
}
