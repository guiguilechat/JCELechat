package fr.guiguilechat.jcelechat.model.sde.types.skins;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.SKINs;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Max1YearSKIN
    extends SKINs
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Max1YearSKIN.MetaGroup METAGROUP = new Max1YearSKIN.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Max1YearSKIN> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Max1YearSKIN>
    {
        public static final String RESOURCE_PATH = "SDE/types/skins/Max1YearSKIN.yaml";
        private Map<Integer, Max1YearSKIN> cache = (null);

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
        public synchronized Map<Integer, Max1YearSKIN> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Max1YearSKIN.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, Max1YearSKIN> types;
        }
    }
}
