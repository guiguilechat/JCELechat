package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_corporations_corporation_id_wallets_division_transactions {
    /**
     * client_id integer
     */
    public int client_id;
    /**
     * Date and time of transaction
     */
    public String date;
    /**
     * is_buy boolean
     */
    public boolean is_buy;
    /**
     * -1 if there is no corresponding wallet journal entry
     */
    public long journal_ref_id;
    /**
     * location_id integer
     */
    public long location_id;
    /**
     * quantity integer
     */
    public int quantity;
    /**
     * Unique transaction ID
     */
    public long transaction_id;
    /**
     * type_id integer
     */
    public int type_id;
    /**
     * Amount paid per unit
     */
    public double unit_price;
}
