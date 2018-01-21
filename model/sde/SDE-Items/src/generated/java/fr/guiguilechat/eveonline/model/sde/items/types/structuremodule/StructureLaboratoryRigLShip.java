
package fr.guiguilechat.eveonline.model.sde.items.types.structuremodule;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureLaboratoryRigLShip
    extends StructureModule
{

    /**
     * High-sec bonus on structure rigs.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double StructureRigBonus2;
    /**
     * How much of the upgrade capacity is used when this is fitted to a ship.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double UpgradeCost;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CanFitShipGroup01;
    /**
     * Tech level of an item
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double TechLevel;
    /**
     * The maximum hitpoints of an object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Hp;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RigSize;
    /**
     * High-sec bonus on structure rigs.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double StructureRigBonus1;
    public final static String RESOURCE_PATH = "SDE/items/structuremodule/StructureLaboratoryRigLShip.yaml";
    private static LinkedHashMap<String, StructureLaboratoryRigLShip> cache = (null);

    @Override
    public int getGroupId() {
        return  1605;
    }

    @Override
    public Class<?> getGroup() {
        return StructureLaboratoryRigLShip.class;
    }

    public static LinkedHashMap<String, StructureLaboratoryRigLShip> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StructureLaboratoryRigLShip.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, StructureLaboratoryRigLShip> items;

    }

}
