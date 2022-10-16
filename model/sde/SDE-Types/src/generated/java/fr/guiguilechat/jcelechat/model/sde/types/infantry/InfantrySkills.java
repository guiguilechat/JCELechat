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

public class InfantrySkills
    extends Infantry
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final InfantrySkills.MetaGroup METAGROUP = new InfantrySkills.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<InfantrySkills> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<InfantrySkills>
    {
        public static final String RESOURCE_PATH = "SDE/types/infantry/InfantrySkills.yaml";
        private Map<String, InfantrySkills> cache = (null);

        @Override
        public IMetaCategory<? super InfantrySkills> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  351648;
        }

        @Override
        public String getName() {
            return "InfantrySkills";
        }

        @Override
        public synchronized Map<String, InfantrySkills> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(InfantrySkills.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<String, InfantrySkills> types;
        }
    }
}
