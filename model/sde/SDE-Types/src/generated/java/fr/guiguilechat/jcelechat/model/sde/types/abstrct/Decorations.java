package fr.guiguilechat.jcelechat.model.sde.types.abstrct;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Abstrct;
import org.yaml.snakeyaml.Yaml;

public class Decorations
    extends Abstrct
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Decorations.MetaGroup METAGROUP = new Decorations.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Decorations> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Decorations>
    {
        public static final String RESOURCE_PATH = "SDE/types/abstrct/Decorations.yaml";
        private Map<String, Decorations> cache = (null);

        @Override
        public IMetaCategory<? super Decorations> category() {
            return Abstrct.METACAT;
        }

        @Override
        public int getGroupId() {
            return  937;
        }

        @Override
        public String getName() {
            return "Decorations";
        }

        @Override
        public synchronized Map<String, Decorations> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Decorations.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Decorations> types;
        }
    }
}
