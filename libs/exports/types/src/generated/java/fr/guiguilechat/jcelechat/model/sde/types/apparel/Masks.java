package fr.guiguilechat.jcelechat.model.sde.types.apparel;

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
import fr.guiguilechat.jcelechat.model.sde.types.Apparel;

public class Masks
    extends Apparel
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Masks.MetaGroup METAGROUP = new Masks.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Masks> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Masks>
    {
        public static final String RESOURCE_PATH = "SDE/types/apparel/Masks.yaml";
        private Map<Integer, Masks> cache = (null);

        @Override
        public IMetaCategory<? super Masks> category() {
            return Apparel.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4057;
        }

        @Override
        public String getName() {
            return "Masks";
        }

        @Override
        public synchronized Map<Integer, Masks> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Masks.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Masks> types;
        }
    }
}
