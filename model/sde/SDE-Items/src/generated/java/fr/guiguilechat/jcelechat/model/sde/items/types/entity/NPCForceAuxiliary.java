package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCForceAuxiliary
    extends Entity
{
    public final static NPCForceAuxiliary.MetaGroup METAGROUP = new NPCForceAuxiliary.MetaGroup();

    @Override
    public IMetaGroup<NPCForceAuxiliary> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCForceAuxiliary>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/NPCForceAuxiliary.yaml";
        private Map<String, NPCForceAuxiliary> cache = (null);

        @Override
        public IMetaCategory<? super NPCForceAuxiliary> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1879;
        }

        @Override
        public String getName() {
            return "NPCForceAuxiliary";
        }

        @Override
        public synchronized Map<String, NPCForceAuxiliary> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(NPCForceAuxiliary.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCForceAuxiliary> items;
        }
    }
}