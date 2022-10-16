package fr.guiguilechat.jcelechat.model.sde.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class ShieldResistanceShiftHardenerBlueprint
    extends Blueprint
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final ShieldResistanceShiftHardenerBlueprint.MetaGroup METAGROUP = new ShieldResistanceShiftHardenerBlueprint.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<ShieldResistanceShiftHardenerBlueprint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ShieldResistanceShiftHardenerBlueprint>
    {
        public static final String RESOURCE_PATH = "SDE/types/blueprint/ShieldResistanceShiftHardenerBlueprint.yaml";
        private Map<String, ShieldResistanceShiftHardenerBlueprint> cache = (null);

        @Override
        public IMetaCategory<? super ShieldResistanceShiftHardenerBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1723;
        }

        @Override
        public String getName() {
            return "ShieldResistanceShiftHardenerBlueprint";
        }

        @Override
        public synchronized Map<String, ShieldResistanceShiftHardenerBlueprint> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ShieldResistanceShiftHardenerBlueprint.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ShieldResistanceShiftHardenerBlueprint> types;
        }
    }
}
