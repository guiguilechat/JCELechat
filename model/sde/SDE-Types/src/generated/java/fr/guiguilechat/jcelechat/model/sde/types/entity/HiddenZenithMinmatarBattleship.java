package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithMinmatarBattleship
    extends Entity
{
    public static final HiddenZenithMinmatarBattleship.MetaGroup METAGROUP = new HiddenZenithMinmatarBattleship.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithMinmatarBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithMinmatarBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/HiddenZenithMinmatarBattleship.yaml";
        private Map<String, HiddenZenithMinmatarBattleship> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithMinmatarBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1798;
        }

        @Override
        public String getName() {
            return "HiddenZenithMinmatarBattleship";
        }

        @Override
        public synchronized Map<String, HiddenZenithMinmatarBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(HiddenZenithMinmatarBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithMinmatarBattleship> types;
        }
    }
}
