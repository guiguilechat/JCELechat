package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularIndustrial
    extends Entity
{
    public static final IrregularIndustrial.MetaGroup METAGROUP = new IrregularIndustrial.MetaGroup();

    @Override
    public IMetaGroup<IrregularIndustrial> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularIndustrial>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/IrregularIndustrial.yaml";
        private Map<String, IrregularIndustrial> cache = (null);

        @Override
        public IMetaCategory<? super IrregularIndustrial> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1895;
        }

        @Override
        public String getName() {
            return "IrregularIndustrial";
        }

        @Override
        public synchronized Map<String, IrregularIndustrial> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(IrregularIndustrial.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularIndustrial> items;
        }
    }
}
