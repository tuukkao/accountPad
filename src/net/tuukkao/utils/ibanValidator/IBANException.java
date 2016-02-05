package net.tuukkao.utils.ibanValidator;

/**
 * Base exception for all Iban-related exceptions.
 *
 * @author Tuukka Ojala
 */
class IBANException extends Exception {
    private IBANCountry ibanCountry;
    
    public IBANException(IBANCountry ibanCountry) {
        this.ibanCountry = ibanCountry;
    }
    
    /**
     * Returns the detected {@link IBANCountry} instance or null if one couldn't be detected.
     *
     * @return The detected IBANCountry instance or null if one couldn't be detected.
     */
    public IBANCountry getIBANCountry() {
        return ibanCountry;
    }
}

