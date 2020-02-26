package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class Titan
    extends Entity
{
    public static final Titan.MetaGroup METAGROUP = new Titan.MetaGroup();

    @Override
    public IMetaGroup<Titan> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Titan>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/Titan.yaml";
        private Map<String, Titan> cache = (null);

        @Override
        public IMetaCategory<? super Titan> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1878;
        }

        @Override
        public String getName() {
            return "Titan";
        }

        @Override
        public synchronized Map<String, Titan> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Titan.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Titan> types;
        }
    }
}
