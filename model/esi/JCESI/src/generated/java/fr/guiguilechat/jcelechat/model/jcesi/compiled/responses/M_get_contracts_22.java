package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class M_get_contracts_22 {
    /**
     * Who will accept the contract
     */
    public int acceptor_id;
    /**
     * ID to whom the contract is assigned, can be corporation or character ID
     */
    public int assignee_id;
    /**
     * To whom the contract is available
     */
    public String availability;
    /**
     * Buyout price (for Auctions only)
     */
    public double buyout;
    /**
     * Collateral price (for Couriers only)
     */
    public double collateral;
    /**
     * contract_id integer
     */
    public int contract_id;
    /**
     * Date of confirmation of contract
     */
    public String date_accepted;
    /**
     * Date of completed of contract
     */
    public String date_completed;
    /**
     * Expiration date of the contract
     */
    public String date_expired;
    /**
     * Сreation date of the contract
     */
    public String date_issued;
    /**
     * Number of days to perform the contract
     */
    public int days_to_complete;
    /**
     * End location ID (for Couriers contract)
     */
    public long end_location_id;
    /**
     * true if the contract was issued on behalf of the issuer's corporation
     */
    public boolean for_corporation;
    /**
     * Character's corporation ID for the issuer
     */
    public int issuer_corporation_id;
    /**
     * Character ID for the issuer
     */
    public int issuer_id;
    /**
     * Price of contract (for ItemsExchange and Auctions)
     */
    public double price;
    /**
     * Remuneration for contract (for Couriers only)
     */
    public double reward;
    /**
     * Start location ID (for Couriers contract)
     */
    public long start_location_id;
    /**
     * Status of the the contract
     */
    public String status;
    /**
     * Title of the contract
     */
    public String title;
    /**
     * Type of the contract
     */
    public String type;
    /**
     * Volume of items in the contract
     */
    public double volume;
}
