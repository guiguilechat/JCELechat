package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithMinmatarCapital
    extends Entity
{
    public static final HiddenZenithMinmatarCapital.MetaGroup METAGROUP = new HiddenZenithMinmatarCapital.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithMinmatarCapital> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithMinmatarCapital>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/HiddenZenithMinmatarCapital.yaml";
        private Map<String, HiddenZenithMinmatarCapital> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithMinmatarCapital> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1807;
        }

        @Override
        public String getName() {
            return "HiddenZenithMinmatarCapital";
        }

        @Override
        public synchronized Map<String, HiddenZenithMinmatarCapital> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(HiddenZenithMinmatarCapital.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithMinmatarCapital> types;
        }
    }
}
