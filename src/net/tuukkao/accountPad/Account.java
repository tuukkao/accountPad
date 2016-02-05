package net.tuukkao.accountPad;

/*
 * Stores information about a stored bank account.
 * It also has utility methods for retrieving and formatting the information.
 * 
 * @author Tuukka Ojala
 */
public class Account {
    private String name;
    private String iban;
    
    public Account(String name, String iban) {
        this.name = name;
        this.iban = iban;
    }
    
    public String getIBAN() {
        return this.iban;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setIBAN(String iban) {
        this.iban = iban;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the IBAN formatted in 4-character groups.
     * This makes the IBAN easier to read than a continuous string of characters.
     *
     * @return The formatted IBAN.
     */
    public String getFormattedIBAN() {
        StringBuilder formattedIBAN = new StringBuilder();
        
        for (int i = 0; i < iban.length(); i ++) {
            formattedIBAN.append(iban.charAt(i));
            
            if ((i +1) % 4 == 0) {
                formattedIBAN.append(' ');
            }
        }
        
        return formattedIBAN.toString().toUpperCase();
    }
    
    /**
     * Makes a string out of the name and IBVAN.
     * The string is formatted as name: IBAN
     *
     * @return The formatted string.
     */
    public String toString() {
        return String.format("%s: %s", name, getFormattedIBAN());
    }
}
