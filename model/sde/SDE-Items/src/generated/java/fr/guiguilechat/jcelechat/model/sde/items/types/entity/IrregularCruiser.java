package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularCruiser
    extends Entity
{
    public final static IrregularCruiser.MetaGroup METAGROUP = new IrregularCruiser.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/entity/IrregularCruiser.yaml";
    private static Map<String, IrregularCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1665;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<IrregularCruiser> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, IrregularCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(IrregularCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, IrregularCruiser> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<IrregularCruiser>
    {

        @Override
        public MetaCategory<? super IrregularCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public String getName() {
            return "IrregularCruiser";
        }

        @Override
        public Collection<IrregularCruiser> items() {
            return (load().values());
        }
    }
}
