package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class InvadingPrecursorEntities
    extends Entity
{
    public static final InvadingPrecursorEntities.MetaGroup METAGROUP = new InvadingPrecursorEntities.MetaGroup();

    @Override
    public IMetaGroup<InvadingPrecursorEntities> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<InvadingPrecursorEntities>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/InvadingPrecursorEntities.yaml";
        private Map<String, InvadingPrecursorEntities> cache = (null);

        @Override
        public IMetaCategory<? super InvadingPrecursorEntities> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4028;
        }

        @Override
        public String getName() {
            return "InvadingPrecursorEntities";
        }

        @Override
        public synchronized Map<String, InvadingPrecursorEntities> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(InvadingPrecursorEntities.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, InvadingPrecursorEntities> items;
        }
    }
}
