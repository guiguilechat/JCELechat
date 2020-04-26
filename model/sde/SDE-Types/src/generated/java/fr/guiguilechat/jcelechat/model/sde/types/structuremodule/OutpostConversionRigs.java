package fr.guiguilechat.jcelechat.model.sde.types.structuremodule;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class OutpostConversionRigs
    extends StructureModule
{
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AttributeAdvCompManufactureTime;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AttributeCapCompManufactureTime;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AttributeCopyTime;
    /**
     * Bonus on Material decrease for engineering rigs
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double AttributeEngRigMatBonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AttributeEquipmentManufactureTime;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AttributeInventionCosts;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AttributeInventionTime;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AttributeMEResearchTime;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AttributeResearchCosts;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AttributeT1ShipManufactureTime;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AttributeT2ShipManufactureTime;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AttributeTEResearchTime;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipGroup01;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ConversionRigHPCapBonus;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Hp;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxGroupFitted;
    /**
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * The factor by which the structure modifies the using pilot's refining yield rate.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.5)
    public double RefiningYieldMultiplier;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSize;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureRigMaxTargetBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int StructureRigMaxTargetRangeBonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureRigPDCapUseBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int StructureRigPDRangeBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int StructureRigScanResBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureServiceRoleBonus;
    /**
     * How much of the upgrade capacity is used when this is fitted to a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeCost;
    public static final OutpostConversionRigs.MetaGroup METAGROUP = new OutpostConversionRigs.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2775 :
            {
                return AttributeAdvCompManufactureTime;
            }
            case  2776 :
            {
                return AttributeCapCompManufactureTime;
            }
            case  2780 :
            {
                return AttributeCopyTime;
            }
            case  2594 :
            {
                return AttributeEngRigMatBonus;
            }
            case  2777 :
            {
                return AttributeEquipmentManufactureTime;
            }
            case  2783 :
            {
                return AttributeInventionCosts;
            }
            case  2781 :
            {
                return AttributeInventionTime;
            }
            case  2778 :
            {
                return AttributeMEResearchTime;
            }
            case  2782 :
            {
                return AttributeResearchCosts;
            }
            case  2773 :
            {
                return AttributeT1ShipManufactureTime;
            }
            case  2774 :
            {
                return AttributeT2ShipManufactureTime;
            }
            case  2779 :
            {
                return AttributeTEResearchTime;
            }
            case  1298 :
            {
                return CanFitShipGroup01;
            }
            case  2772 :
            {
                return ConversionRigHPCapBonus;
            }
            case  9 :
            {
                return Hp;
            }
            case  1544 :
            {
                return MaxGroupFitted;
            }
            case  1692 :
            {
                return MetaGroupID;
            }
            case  633 :
            {
                return MetaLevel;
            }
            case  717 :
            {
                return RefiningYieldMultiplier;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  1547 :
            {
                return RigSize;
            }
            case  2434 :
            {
                return StructureRigMaxTargetBonus;
            }
            case  2748 :
            {
                return StructureRigMaxTargetRangeBonus;
            }
            case  2437 :
            {
                return StructureRigPDCapUseBonus;
            }
            case  2436 :
            {
                return StructureRigPDRangeBonus;
            }
            case  2435 :
            {
                return StructureRigScanResBonus;
            }
            case  2339 :
            {
                return StructureServiceRoleBonus;
            }
            case  1153 :
            {
                return UpgradeCost;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<OutpostConversionRigs> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<OutpostConversionRigs>
    {
        public static final String RESOURCE_PATH = "SDE/types/structuremodule/OutpostConversionRigs.yaml";
        private Map<String, OutpostConversionRigs> cache = (null);

        @Override
        public IMetaCategory<? super OutpostConversionRigs> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1984;
        }

        @Override
        public String getName() {
            return "OutpostConversionRigs";
        }

        @Override
        public synchronized Map<String, OutpostConversionRigs> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(OutpostConversionRigs.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, OutpostConversionRigs> types;
        }
    }
}
