package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularBattlecruiser
    extends Entity
{
    public static final IrregularBattlecruiser.MetaGroup METAGROUP = new IrregularBattlecruiser.MetaGroup();

    @Override
    public IMetaGroup<IrregularBattlecruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularBattlecruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/IrregularBattlecruiser.yaml";
        private Map<String, IrregularBattlecruiser> cache = (null);

        @Override
        public IMetaCategory<? super IrregularBattlecruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1666;
        }

        @Override
        public String getName() {
            return "IrregularBattlecruiser";
        }

        @Override
        public synchronized Map<String, IrregularBattlecruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(IrregularBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularBattlecruiser> items;
        }
    }
}
