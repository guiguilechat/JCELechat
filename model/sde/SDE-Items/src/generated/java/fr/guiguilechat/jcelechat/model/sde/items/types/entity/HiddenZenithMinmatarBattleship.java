package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithMinmatarBattleship
    extends Entity
{
    public final static HiddenZenithMinmatarBattleship.MetaGroup METAGROUP = new HiddenZenithMinmatarBattleship.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithMinmatarBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithMinmatarBattleship>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithMinmatarBattleship.yaml";
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
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithMinmatarBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithMinmatarBattleship> items;
        }
    }
}