package fr.guiguilechat.eveonline.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class UnpublishedStructureModuleAndRigBlueprints
    extends Blueprint
{
    /**
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureItemVisualFlag;
    /**
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    public final static String RESOURCE_PATH = "SDE/items/blueprint/UnpublishedStructureModuleAndRigBlueprints.yaml";
    private static LinkedHashMap<String, UnpublishedStructureModuleAndRigBlueprints> cache = (null);

    @Override
    public int getGroupId() {
        return  1709;
    }

    @Override
    public Class<?> getGroup() {
        return UnpublishedStructureModuleAndRigBlueprints.class;
    }

    public static synchronized LinkedHashMap<String, UnpublishedStructureModuleAndRigBlueprints> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(UnpublishedStructureModuleAndRigBlueprints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, UnpublishedStructureModuleAndRigBlueprints> items;
    }
}
