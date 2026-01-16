package fr.guiguilechat.jcelechat.model.sde.types.infantry;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Infantry;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class SalvageDecryptors
    extends Infantry
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final SalvageDecryptors.MetaGroup METAGROUP = new SalvageDecryptors.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<SalvageDecryptors> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SalvageDecryptors>
    {
        public static final String RESOURCE_PATH = "SDE/types/infantry/SalvageDecryptors.yaml";
        private Map<Integer, SalvageDecryptors> cache = (null);

        @Override
        public IMetaCategory<? super SalvageDecryptors> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  367776;
        }

        @Override
        public String getName() {
            return "SalvageDecryptors";
        }

        @Override
        public synchronized Map<Integer, SalvageDecryptors> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SalvageDecryptors.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, SalvageDecryptors> types;
        }
    }
}
