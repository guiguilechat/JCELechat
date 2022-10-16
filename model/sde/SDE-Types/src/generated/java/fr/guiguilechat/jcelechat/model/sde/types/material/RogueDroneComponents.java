package fr.guiguilechat.jcelechat.model.sde.types.material;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Material;
import org.yaml.snakeyaml.Yaml;

public class RogueDroneComponents
    extends Material
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final RogueDroneComponents.MetaGroup METAGROUP = new RogueDroneComponents.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<RogueDroneComponents> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RogueDroneComponents>
    {
        public static final String RESOURCE_PATH = "SDE/types/material/RogueDroneComponents.yaml";
        private Map<String, RogueDroneComponents> cache = (null);

        @Override
        public IMetaCategory<? super RogueDroneComponents> category() {
            return Material.METACAT;
        }

        @Override
        public int getGroupId() {
            return  886;
        }

        @Override
        public String getName() {
            return "RogueDroneComponents";
        }

        @Override
        public synchronized Map<String, RogueDroneComponents> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(RogueDroneComponents.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RogueDroneComponents> types;
        }
    }
}
