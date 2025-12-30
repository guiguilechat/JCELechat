package fr.guiguilechat.jcelechat.model.sde.types.abstrct;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Abstrct;

public class Miscellaneous
    extends Abstrct
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Miscellaneous.MetaGroup METAGROUP = new Miscellaneous.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Miscellaneous> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Miscellaneous>
    {
        public static final String RESOURCE_PATH = "SDE/types/abstrct/Miscellaneous.yaml";
        private Map<Integer, Miscellaneous> cache = (null);

        @Override
        public IMetaCategory<? super Miscellaneous> category() {
            return Abstrct.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1319;
        }

        @Override
        public String getName() {
            return "Miscellaneous";
        }

        @Override
        public synchronized Map<Integer, Miscellaneous> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Miscellaneous.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Miscellaneous> types;
        }
    }
}
