package net.tuukkao.utils.ibanValidator;

/**
 * All countries that support IBAN and their respective IBAN lengths.
 *
 * @author Tuukka Ojala
 */
public enum IBANCountry {
    AL("Albania", 28),
    AD("Andorra", 24),
    AT("Austria", 20),
    AZ("Republic of Azerbaijan", 28),
    BH("Bahrain", 22),
    BE("Belgium", 16),
    BA("Bosnia And Herzegovina", 20),
    BR("Brazil", 29),
    BG("Bulgaria", 22),
    CR("Costa Rica", 21),
    HR("Croatia", 21),
    CY("Cyprus", 28),
    CZ("Czech Republic", 24),
    DK("Denmark", 18),
    DO("Dominican Republic", 28),
    EE("Estonia", 20),
    FI("Finland", 18),
    FR("France", 27),
    GE("Georgia", 22),
    DE("Germany", 22),
    GI("Gibraltar", 23),
    GR("Greece", 27),
    GT("Guatemala", 28),
    HU("Hungary", 28),
    IS("Iceland", 26),
    IE("Ireland", 22),
    IL("Israel", 23),
    IT("Italy", 27),
    JO("Jordan", 30),
    KZ("Kazakhstan", 20),
    XK("Republic of Kosovo", 20),
    KW("Kuwait", 30),
    LV("Latvia", 21),
    LB("Lebanon", 28),
    LI("Principality of Liechtenstein", 21),
    LT("Lithuania", 21),
    LU("Luxembourg", 20),
    MK("Makedonia", 19),
    MT("Malta", 31),
    MR("Mauritania", 27),
    MU("Mauritius", 30),
    MD("Moldova", 24),
    MC("Monaco", 27),
    ME("Montenegro", 22),
    NL("the Netherlands", 18),
    NO("Norway", 15),
    PK("Pakistan", 24),
    PS("State of Palestine", 29),
    PL("Poland", 28),
    PT("Portugal", 25),
    RO("Romania", 24),
    QA("Qatar", 29),
    LC("Saint Lucia", 32),
    SM("San Marino", 27),
    ST("Sao Tome And Principe", 25),
    SA("Saudi Arabia", 24),
    RS("Serbia", 22),
    SK("Slovak Republic", 24),
    SI("Slovenia", 19),
    ES("Spain", 24),
    SE("Sweden", 24),
    CH("Switzerland", 21),
    TL("Timor-Leste", 23),
    TN("Tunisia", 24),
    TR("Turkey", 26),
    UA("Ukraine", 29),
    AE("United Arab Emirates", 23),
    GB("United Kingdom", 22),
    VG("Virgin Islands, British", 24);
    
    /** The full name of the country. */
    private String name;
    /** The length of this country's IBAN. */
    private final int length;
    
    /**
     * Constructs a new IBANCountry with the specified name and length.
     *
     * @param name The full name of this country.
     * @param length The length of this country's IBAN.
     */
    IBANCountry(String name, int length) {
        this.name = name;
        this.length = length;
    }
    
    /**
     * Returns the length of IBAN's for this country.
     *
     * @return The length of IBAN's for this country.
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Returns the country's full name.
     *
     * @return This country's full name.
     */
    public String getName() {
        return name;
    }
}
