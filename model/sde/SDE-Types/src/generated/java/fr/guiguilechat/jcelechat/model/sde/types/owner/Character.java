package fr.guiguilechat.jcelechat.model.sde.types.owner;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Owner;
import org.yaml.snakeyaml.Yaml;

public class Character
    extends Owner
{
    public static final Character.MetaGroup METAGROUP = new Character.MetaGroup();

    @Override
    public IMetaGroup<Character> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Character>
    {
        public static final String RESOURCE_PATH = "SDE/types/owner/Character.yaml";
        private Map<String, Character> cache = (null);

        @Override
        public IMetaCategory<? super Character> category() {
            return Owner.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1;
        }

        @Override
        public String getName() {
            return "Character";
        }

        @Override
        public synchronized Map<String, Character> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Character.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Character> types;
        }
    }
}
