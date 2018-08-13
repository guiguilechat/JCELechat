package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularFrigate
    extends Entity
{
    public final static IrregularFrigate.MetaGroup METAGROUP = new IrregularFrigate.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/entity/IrregularFrigate.yaml";
    private static Map<String, IrregularFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1568;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<IrregularFrigate> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, IrregularFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(IrregularFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, IrregularFrigate> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<IrregularFrigate>
    {

        @Override
        public MetaCategory<? super IrregularFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public String getName() {
            return "IrregularFrigate";
        }

        @Override
        public Collection<IrregularFrigate> items() {
            return (load().values());
        }
    }
}
