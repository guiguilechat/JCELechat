package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;

public class CyberScanningImplantBlueprints
    extends Blueprint
{
    public final static String RESOURCE_PATH = "SDE/items/blueprint/CyberScanningImplantBlueprints.yaml";
    private static LinkedHashMap<String, CyberScanningImplantBlueprints> cache = (null);

    @Override
    public int getGroupId() {
        return  1948;
    }

    @Override
    public Class<?> getGroup() {
        return CyberScanningImplantBlueprints.class;
    }

    public static synchronized LinkedHashMap<String, CyberScanningImplantBlueprints> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CyberScanningImplantBlueprints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, CyberScanningImplantBlueprints> items;
    }
}
