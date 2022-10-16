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
import org.yaml.snakeyaml.Yaml;

public class PermanentSKIN
    extends SKINs
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final PermanentSKIN.MetaGroup METAGROUP = new PermanentSKIN.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<PermanentSKIN> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PermanentSKIN>
    {
        public static final String RESOURCE_PATH = "SDE/types/skins/PermanentSKIN.yaml";
        private Map<String, PermanentSKIN> cache = (null);

        @Override
        public IMetaCategory<? super PermanentSKIN> category() {
            return SKINs.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1950;
        }

        @Override
        public String getName() {
            return "PermanentSKIN";
        }

        @Override
        public synchronized Map<String, PermanentSKIN> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PermanentSKIN.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PermanentSKIN> types;
        }
    }
}
