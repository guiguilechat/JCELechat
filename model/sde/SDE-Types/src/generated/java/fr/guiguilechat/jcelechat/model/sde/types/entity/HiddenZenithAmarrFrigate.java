package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithAmarrFrigate
    extends Entity
{
    public static final HiddenZenithAmarrFrigate.MetaGroup METAGROUP = new HiddenZenithAmarrFrigate.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithAmarrFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithAmarrFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/HiddenZenithAmarrFrigate.yaml";
        private Map<String, HiddenZenithAmarrFrigate> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithAmarrFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1791;
        }

        @Override
        public String getName() {
            return "HiddenZenithAmarrFrigate";
        }

        @Override
        public synchronized Map<String, HiddenZenithAmarrFrigate> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(HiddenZenithAmarrFrigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithAmarrFrigate> types;
        }
    }
}
