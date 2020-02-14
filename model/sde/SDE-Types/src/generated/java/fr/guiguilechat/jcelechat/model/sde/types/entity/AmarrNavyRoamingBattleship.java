package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AmarrNavyRoamingBattleship
    extends Entity
{
    public static final AmarrNavyRoamingBattleship.MetaGroup METAGROUP = new AmarrNavyRoamingBattleship.MetaGroup();

    @Override
    public IMetaGroup<AmarrNavyRoamingBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AmarrNavyRoamingBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AmarrNavyRoamingBattleship.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(AmarrNavyRoamingBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AmarrNavyRoamingBattleship> types;
        }
    }
}
