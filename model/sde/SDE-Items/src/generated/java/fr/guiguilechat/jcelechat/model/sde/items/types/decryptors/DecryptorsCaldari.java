package fr.guiguilechat.jcelechat.model.sde.items.types.decryptors;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsCaldari
    extends Decryptors
{
    public final static DecryptorsCaldari.MetaGroup METAGROUP = new DecryptorsCaldari.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/decryptors/DecryptorsCaldari.yaml";
    private static Map<String, DecryptorsCaldari> cache = (null);

    @Override
    public int getGroupId() {
        return  731;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<DecryptorsCaldari> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, DecryptorsCaldari> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DecryptorsCaldari.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, DecryptorsCaldari> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<DecryptorsCaldari>
    {

        @Override
        public MetaCategory<? super DecryptorsCaldari> category() {
            return Decryptors.METACAT;
        }

        @Override
        public String getName() {
            return "DecryptorsCaldari";
        }

        @Override
        public Collection<DecryptorsCaldari> items() {
            return (load().values());
        }
    }
}
