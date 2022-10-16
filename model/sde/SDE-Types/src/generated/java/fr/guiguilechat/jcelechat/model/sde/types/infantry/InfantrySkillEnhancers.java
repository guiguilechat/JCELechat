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
import org.yaml.snakeyaml.Yaml;

public class InfantrySkillEnhancers
    extends Infantry
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final InfantrySkillEnhancers.MetaGroup METAGROUP = new InfantrySkillEnhancers.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<InfantrySkillEnhancers> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<InfantrySkillEnhancers>
    {
        public static final String RESOURCE_PATH = "SDE/types/infantry/InfantrySkillEnhancers.yaml";
        private Map<String, InfantrySkillEnhancers> cache = (null);

        @Override
        public IMetaCategory<? super InfantrySkillEnhancers> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  354641;
        }

        @Override
        public String getName() {
            return "InfantrySkillEnhancers";
        }

        @Override
        public synchronized Map<String, InfantrySkillEnhancers> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(InfantrySkillEnhancers.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, InfantrySkillEnhancers> types;
        }
    }
}
