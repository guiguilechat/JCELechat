package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithMinmatarFrigate
    extends Entity
{
    public static final HiddenZenithMinmatarFrigate.MetaGroup METAGROUP = new HiddenZenithMinmatarFrigate.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithMinmatarFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithMinmatarFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/HiddenZenithMinmatarFrigate.yaml";
        private Map<String, HiddenZenithMinmatarFrigate> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithMinmatarFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1800;
        }

        @Override
        public String getName() {
            return "HiddenZenithMinmatarFrigate";
        }

        @Override
        public synchronized Map<String, HiddenZenithMinmatarFrigate> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(HiddenZenithMinmatarFrigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithMinmatarFrigate> types;
        }
    }
}
