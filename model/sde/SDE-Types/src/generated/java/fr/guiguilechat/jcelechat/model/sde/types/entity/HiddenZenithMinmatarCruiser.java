package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithMinmatarCruiser
    extends Entity
{
    public static final HiddenZenithMinmatarCruiser.MetaGroup METAGROUP = new HiddenZenithMinmatarCruiser.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithMinmatarCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithMinmatarCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/HiddenZenithMinmatarCruiser.yaml";
        private Map<String, HiddenZenithMinmatarCruiser> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithMinmatarCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1799;
        }

        @Override
        public String getName() {
            return "HiddenZenithMinmatarCruiser";
        }

        @Override
        public synchronized Map<String, HiddenZenithMinmatarCruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(HiddenZenithMinmatarCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithMinmatarCruiser> types;
        }
    }
}
