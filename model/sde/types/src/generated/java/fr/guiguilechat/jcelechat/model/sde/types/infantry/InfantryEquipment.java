package fr.guiguilechat.jcelechat.model.sde.types.infantry;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Infantry;

public class InfantryEquipment
    extends Infantry
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final InfantryEquipment.MetaGroup METAGROUP = new InfantryEquipment.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<InfantryEquipment> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<InfantryEquipment>
    {
        public static final String RESOURCE_PATH = "SDE/types/infantry/InfantryEquipment.yaml";
        private Map<Integer, InfantryEquipment> cache = (null);

        @Override
        public IMetaCategory<? super InfantryEquipment> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  351844;
        }

        @Override
        public String getName() {
            return "InfantryEquipment";
        }

        @Override
        public synchronized Map<Integer, InfantryEquipment> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(InfantryEquipment.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, InfantryEquipment> types;
        }
    }
}
