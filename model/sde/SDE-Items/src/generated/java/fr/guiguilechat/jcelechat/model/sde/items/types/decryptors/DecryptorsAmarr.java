package fr.guiguilechat.jcelechat.model.sde.items.types.decryptors;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsAmarr
    extends Decryptors
{
    public final static DecryptorsAmarr.MetaGroup METAGROUP = new DecryptorsAmarr.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/decryptors/DecryptorsAmarr.yaml";
    private static Map<String, DecryptorsAmarr> cache = (null);

    @Override
    public int getGroupId() {
        return  728;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<DecryptorsAmarr> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, DecryptorsAmarr> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DecryptorsAmarr.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, DecryptorsAmarr> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<DecryptorsAmarr>
    {

        @Override
        public MetaCategory<? super DecryptorsAmarr> category() {
            return Decryptors.METACAT;
        }

        @Override
        public String getName() {
            return "DecryptorsAmarr";
        }

        @Override
        public Collection<DecryptorsAmarr> items() {
            return (load().values());
        }
    }
}
