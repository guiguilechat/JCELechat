package fr.guiguilechat.eveonline.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class StarbaseEnergyNeutralizingBatteryBlueprints
    extends Blueprint
{
    /**
     * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double IndustryBlueprintRank;
    public final static String RESOURCE_PATH = "SDE/items/blueprint/StarbaseEnergyNeutralizingBatteryBlueprints.yaml";
    private static LinkedHashMap<String, StarbaseEnergyNeutralizingBatteryBlueprints> cache = (null);

    @Override
    public int getGroupId() {
        return  860;
    }

    @Override
    public Class<?> getGroup() {
        return StarbaseEnergyNeutralizingBatteryBlueprints.class;
    }

    public static LinkedHashMap<String, StarbaseEnergyNeutralizingBatteryBlueprints> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StarbaseEnergyNeutralizingBatteryBlueprints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, StarbaseEnergyNeutralizingBatteryBlueprints> items;
    }
}
