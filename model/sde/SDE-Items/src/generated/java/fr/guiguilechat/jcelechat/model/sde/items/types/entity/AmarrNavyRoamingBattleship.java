package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AmarrNavyRoamingBattleship
    extends Entity
{
    public final static AmarrNavyRoamingBattleship.MetaGroup METAGROUP = new AmarrNavyRoamingBattleship.MetaGroup();

    @Override
    public IMetaGroup<AmarrNavyRoamingBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AmarrNavyRoamingBattleship>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AmarrNavyRoamingBattleship.yaml";
        private Map<String, AmarrNavyRoamingBattleship> cache = (null);

        @Override
        public IMetaCategory<? super AmarrNavyRoamingBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1402;
        }

        @Override
        public String getName() {
            return "AmarrNavyRoamingBattleship";
        }

        @Override
        public synchronized Map<String, AmarrNavyRoamingBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AmarrNavyRoamingBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AmarrNavyRoamingBattleship> items;
        }
    }
}
