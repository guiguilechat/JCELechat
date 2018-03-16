package net.evetech.esi.responses;

public class R_get_corporations_corporation_id_wallets_division_journal {
    public String date;
    public long ref_id;
    public String ref_type;
    public int first_party_id;
    public String first_party_type;
    public int second_party_id;
    public String second_party_type;
    public double amount;
    public double balance;
    public String reason;
    public int tax_receiver_id;
    public double tax;
    public R_get_corporations_corporation_id_wallets_division_journal_extra_info extra_info;
}
