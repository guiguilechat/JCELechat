package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.PrimaryAttribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SecondaryAttribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.SkillTimeConstant;
import fr.guiguilechat.jcelechat.model.sde.types.skill.*;

public abstract class Skill
    extends EveType
{
    /**
     * Only refers to another dogma attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int primaryattribute;
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
     * Only refers to another dogma attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int secondaryattribute;
    /**
     * This attribute is a multiplier to the number of skill points required to train. Skill points required to train a skill = 250 * skillTimeConstant * sqrt(32)^(skillLevel - 1)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double skilltimeconstant;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {SkillTimeConstant.INSTANCE, PrimaryAttribute.INSTANCE, SecondaryAttribute.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE })));
    public static final Skill.MetaCat METACAT = new Skill.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  180 :
            {
                return primaryattribute;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  181 :
            {
                return secondaryattribute;
            }
            case  275 :
            {
                return skilltimeconstant;
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
    public IMetaCategory<Skill> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Skill>
    {

        @Override
        public int getCategoryId() {
            return  16;
        }

        @Override
        public String getName() {
            return "Skill";
        }

        @Override
        public Collection<IMetaGroup<? extends Skill>> groups() {
            return Arrays.asList(Gunnery.METAGROUP, Missiles.METAGROUP, SpaceshipCommand.METAGROUP, FleetSupport.METAGROUP, CorporationManagement.METAGROUP, Production.METAGROUP, Rigging.METAGROUP, Science.METAGROUP, ElectronicSystems.METAGROUP, Drones.METAGROUP, Trade.METAGROUP, Navigation.METAGROUP, Social.METAGROUP, Sequencing.METAGROUP, Shields.METAGROUP, Armor.METAGROUP, Targeting.METAGROUP, Engineering.METAGROUP, Scanning.METAGROUP, ResourceProcessing.METAGROUP, NeuralEnhancement.METAGROUP, Subsystems.METAGROUP, PlanetManagement.METAGROUP, StructureManagement.METAGROUP);
        }
    }
}
