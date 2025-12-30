package fr.guiguilechat.jcelechat.model.sde.types.structuremodule;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;

public class OutpostConversionRigs
    extends StructureModule
{
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int attributeadvcompmanufacturetime;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int attributecapcompmanufacturetime;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int attributecopytime;
    /**
     * Bonus on Material decrease for engineering rigs
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double attributeengrigmatbonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int attributeequipmentmanufacturetime;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int attributeinventioncosts;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int attributeinventiontime;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int attributemeresearchtime;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int attributeresearchcosts;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int attributet1shipmanufacturetime;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int attributet2shipmanufacturetime;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int attributeteresearchtime;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup01;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int conversionrighpcapbonus;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupfitted;
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
    public int metagroupid;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * The factor by which the structure modifies the using pilot's refining yield rate.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.5)
    public double refiningyieldmultiplier;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigsize;
    /**
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureitemvisualflag;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structurerigmaxtargetbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int structurerigmaxtargetrangebonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structurerigpdcapusebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int structurerigpdrangebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int structurerigscanresbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureservicerolebonus;
    /**
     * How much of the upgrade capacity is used when this is fitted to a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradecost;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {UpgradeCost.INSTANCE, StructureRigMaxTargetBonus.INSTANCE, StructureRigScanResBonus.INSTANCE, StructureRigPDRangeBonus.INSTANCE, StructureRigPDCapUseBonus.INSTANCE, MaxGroupFitted.INSTANCE, Hp.INSTANCE, RigSize.INSTANCE, RefiningYieldMultiplier.INSTANCE, CanFitShipGroup01 .INSTANCE, ConversionRigHPCapBonus.INSTANCE, RequiredSkill1Level.INSTANCE, AttributeT1ShipManufactureTime.INSTANCE, AttributeT2ShipManufactureTime.INSTANCE, AttributeAdvCompManufactureTime.INSTANCE, AttributeCapCompManufactureTime.INSTANCE, AttributeEquipmentManufactureTime.INSTANCE, AttributeMEResearchTime.INSTANCE, AttributeTEResearchTime.INSTANCE, MetaGroupID.INSTANCE, AttributeCopyTime.INSTANCE, AttributeInventionTime.INSTANCE, StructureItemVisualFlag.INSTANCE, AttributeResearchCosts.INSTANCE, AttributeInventionCosts.INSTANCE, AttributeEngRigMatBonus.INSTANCE, StructureServiceRoleBonus.INSTANCE, RequiredSkill1 .INSTANCE, MetaLevelOld.INSTANCE, StructureRigMaxTargetRangeBonus.INSTANCE })));
    public static final OutpostConversionRigs.MetaGroup METAGROUP = new OutpostConversionRigs.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2775 :
            {
                return attributeadvcompmanufacturetime;
            }
            case  2776 :
            {
                return attributecapcompmanufacturetime;
            }
            case  2780 :
            {
                return attributecopytime;
            }
            case  2594 :
            {
                return attributeengrigmatbonus;
            }
            case  2777 :
            {
                return attributeequipmentmanufacturetime;
            }
            case  2783 :
            {
                return attributeinventioncosts;
            }
            case  2781 :
            {
                return attributeinventiontime;
            }
            case  2778 :
            {
                return attributemeresearchtime;
            }
            case  2782 :
            {
                return attributeresearchcosts;
            }
            case  2773 :
            {
                return attributet1shipmanufacturetime;
            }
            case  2774 :
            {
                return attributet2shipmanufacturetime;
            }
            case  2779 :
            {
                return attributeteresearchtime;
            }
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  2772 :
            {
                return conversionrighpcapbonus;
            }
            case  9 :
            {
                return hp;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  717 :
            {
                return refiningyieldmultiplier;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  2334 :
            {
                return structureitemvisualflag;
            }
            case  2434 :
            {
                return structurerigmaxtargetbonus;
            }
            case  2748 :
            {
                return structurerigmaxtargetrangebonus;
            }
            case  2437 :
            {
                return structurerigpdcapusebonus;
            }
            case  2436 :
            {
                return structurerigpdrangebonus;
            }
            case  2435 :
            {
                return structurerigscanresbonus;
            }
            case  2339 :
            {
                return structureservicerolebonus;
            }
            case  1153 :
            {
                return upgradecost;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<OutpostConversionRigs> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<OutpostConversionRigs>
    {
        public static final String RESOURCE_PATH = "SDE/types/structuremodule/OutpostConversionRigs.yaml";
        private Map<Integer, OutpostConversionRigs> cache = (null);

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
        public synchronized Map<Integer, OutpostConversionRigs> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(OutpostConversionRigs.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, OutpostConversionRigs> types;
        }
    }
}
