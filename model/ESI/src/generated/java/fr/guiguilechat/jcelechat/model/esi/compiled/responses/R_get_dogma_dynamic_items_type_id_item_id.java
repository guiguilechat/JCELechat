package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

public class R_get_dogma_dynamic_items_type_id_item_id {
    /**
     * The ID of the character who created the item
     */
    public int created_by;
    /**
     * dogma_attributes array
     */
    public M_get_type_dogma_attributes_2 [] dogma_attributes;
    /**
     * dogma_effects array
     */
    public M_get_type_dogma_effects_2 [] dogma_effects;
    /**
     * The type ID of the mutator used to generate the dynamic item.
     */
    public int mutator_type_id;
    /**
     * The type ID of the source item the mutator was applied to create the dynamic item.
     */
    public int source_type_id;
}
