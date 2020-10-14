package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_dogma_dynamic_items_type_id_item_id {
    /**
     * The ID of the character who created the item
     */
    public int created_by;
    /**
     * dogma_attributes array
     */
    public get_dogma_dynamic_items_type_id_item_id_dogma_attributes[] dogma_attributes;
    /**
     * dogma_effects array
     */
    public get_dogma_dynamic_items_type_id_item_id_dogma_effects[] dogma_effects;
    /**
     * The type ID of the mutator used to generate the dynamic item.
     */
    public int mutator_type_id;
    /**
     * The type ID of the source item the mutator was applied to create the dynamic item.
     */
    public int source_type_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_dogma_dynamic_items_type_id_item_id othersame = ((R_get_dogma_dynamic_items_type_id_item_id) other);
        if (created_by!= othersame.created_by) {
            return false;
        }
        if ((dogma_attributes!= othersame.dogma_attributes)&&((dogma_attributes == null)||(!dogma_attributes.equals(othersame.dogma_attributes)))) {
            return false;
        }
        if ((dogma_effects!= othersame.dogma_effects)&&((dogma_effects == null)||(!dogma_effects.equals(othersame.dogma_effects)))) {
            return false;
        }
        if (mutator_type_id!= othersame.mutator_type_id) {
            return false;
        }
        if (source_type_id!= othersame.source_type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((created_by +((dogma_attributes == null)? 0 :dogma_attributes.hashCode()))+((dogma_effects == null)? 0 :dogma_effects.hashCode()))+ mutator_type_id)+ source_type_id);
    }
}
