package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionAmarrEmpireCruiser
    extends Entity
{
    public final static MissionAmarrEmpireCruiser.MetaGroup METAGROUP = new MissionAmarrEmpireCruiser.MetaGroup();

    @Override
    public IMetaGroup<MissionAmarrEmpireCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionAmarrEmpireCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionAmarrEmpireCruiser.yaml";
        private Map<String, MissionAmarrEmpireCruiser> cache = (null);

        @Override
        public IMetaCategory<? super MissionAmarrEmpireCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  668;
        }

        @Override
        public String getName() {
            return "MissionAmarrEmpireCruiser";
        }

        @Override
        public synchronized Map<String, MissionAmarrEmpireCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionAmarrEmpireCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionAmarrEmpireCruiser> items;
        }
    }
}
