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

public class VisualCustomization
    extends Infantry
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final VisualCustomization.MetaGroup METAGROUP = new VisualCustomization.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<VisualCustomization> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<VisualCustomization>
    {
        public static final String RESOURCE_PATH = "SDE/types/infantry/VisualCustomization.yaml";
        private Map<Integer, VisualCustomization> cache = (null);

        @Override
        public IMetaCategory<? super VisualCustomization> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  367594;
        }

        @Override
        public String getName() {
            return "VisualCustomization";
        }

        @Override
        public synchronized Map<Integer, VisualCustomization> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(VisualCustomization.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, VisualCustomization> types;
        }
    }
}
