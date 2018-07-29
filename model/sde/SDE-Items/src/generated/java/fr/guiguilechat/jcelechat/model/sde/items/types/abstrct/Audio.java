package fr.guiguilechat.jcelechat.model.sde.items.types.abstrct;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Abstrct;

public class Audio
    extends Abstrct
{
    public final static String RESOURCE_PATH = "SDE/items/abstrct/Audio.yaml";
    private static LinkedHashMap<String, Audio> cache = (null);

    @Override
    public int getGroupId() {
        return  1109;
    }

    @Override
    public Class<?> getGroup() {
        return Audio.class;
    }

    public static synchronized LinkedHashMap<String, Audio> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Audio.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Audio> items;
    }
}
