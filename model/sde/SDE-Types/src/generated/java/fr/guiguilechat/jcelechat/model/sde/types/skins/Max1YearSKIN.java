package fr.guiguilechat.jcelechat.model.sde.types.skins;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.SKINs;
import org.yaml.snakeyaml.Yaml;

public class Max1YearSKIN
    extends SKINs
{
    public static final Max1YearSKIN.MetaGroup METAGROUP = new Max1YearSKIN.MetaGroup();

    @Override
    public IMetaGroup<Max1YearSKIN> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Max1YearSKIN>
    {
        public static final String RESOURCE_PATH = "SDE/types/skins/Max1YearSKIN.yaml";
        private Map<String, Max1YearSKIN> cache = (null);

        @Override
        public IMetaCategory<? super Max1YearSKIN> category() {
            return SKINs.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1955;
        }

        @Override
        public String getName() {
            return "Max1YearSKIN";
        }

        @Override
        public synchronized Map<String, Max1YearSKIN> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Max1YearSKIN.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Max1YearSKIN> types;
        }
    }
}
