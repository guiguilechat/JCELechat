package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithAmarrBattleship
    extends Entity
{
    public static final HiddenZenithAmarrBattleship.MetaGroup METAGROUP = new HiddenZenithAmarrBattleship.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithAmarrBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithAmarrBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/HiddenZenithAmarrBattleship.yaml";
        private Map<String, HiddenZenithAmarrBattleship> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithAmarrBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1789;
        }

        @Override
        public String getName() {
            return "HiddenZenithAmarrBattleship";
        }

        @Override
        public synchronized Map<String, HiddenZenithAmarrBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(HiddenZenithAmarrBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithAmarrBattleship> types;
        }
    }
}
