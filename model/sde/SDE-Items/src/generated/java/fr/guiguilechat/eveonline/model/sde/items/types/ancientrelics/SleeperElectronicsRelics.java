package fr.guiguilechat.eveonline.model.sde.items.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.AncientRelics;
import org.yaml.snakeyaml.Yaml;

public class SleeperElectronicsRelics
    extends AncientRelics
{
    public final static String RESOURCE_PATH = "SDE/items/ancientrelics/SleeperElectronicsRelics.yaml";
    private static LinkedHashMap<String, SleeperElectronicsRelics> cache = (null);

    @Override
    public int getGroupId() {
        return  990;
    }

    @Override
    public Class<?> getGroup() {
        return SleeperElectronicsRelics.class;
    }

    public static LinkedHashMap<String, SleeperElectronicsRelics> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SleeperElectronicsRelics.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SleeperElectronicsRelics> items;
    }
}
