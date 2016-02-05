package net.tuukkao.utils.ibanValidator;

import java.math.BigInteger;

/**
 * Validates international bank account numbers (IBAN's).
 *
 * This class provides a validator for checking the validity of an IBAN.
 * It detects and reports about incorrect country codes, incorrect IBAN length for the detected country
 * and incorrect characters as indicated by the internal validation system of an IBAN.
 *
 * @author Tuukka Ojala
 */
public class IBANValidator {
    /**
     * Validates an IBAN's country-specific information.
     *
     * This method checks if the provided IBAN has a valid country code and
     * if its length conforms to the country code's specified standard length.
     * 
     * @param iban The IBAN to check.
     * @return An IBANCountry instance of the detected country.
     */
    private static IBANCountry validateCountry(String iban)
    throws IBANUnknownCountryCodeException, IBANIncorrectLengthException {
        String langCode;
        IBANCountry detectedCountry = null;
        
        langCode = iban.substring(0, 2);
        
        for (IBANCountry country: IBANCountry.values()) {
            if (langCode.equals(country.name())) {
                detectedCountry = country;
                break;
            }
        }
        
        if (detectedCountry == null) {
            throw new IBANUnknownCountryCodeException(detectedCountry);
        }
        
        if (iban.length() != detectedCountry.getLength()) {
            throw new IBANIncorrectLengthException(detectedCountry);
        }
        
        return detectedCountry;
    }
    
    /**
     * Converts a character into an IBAN digit.
     *
     * This method returns an integer representation of a character where A=10,
     * B=11 ... Z=35.
     * The conversion works only with upper case characters.
     *
     * @param c The character to be converted
     * @return An IBAN integer of the character.
     */
    private static int charToIbanDigit(char c) {
        // Subtracting 55 from the character's ASCII code gives us the right value.
        // Example: 65 (A) -55 =10
        return (int)c -55;
    }
    
    /**
     * Checks the validity of an IBAN.
     *
     * @param iban The IBAN to be checked.
     * @return true if the IBAN was valid.
     */
    public static boolean validate(String iban)
    throws IBANUnknownCountryCodeException, IBANIncorrectLengthException,
    IBANInvalidException {
        StringBuilder IBANBuilder = new StringBuilder();
        BigInteger IBANNumber;
        IBANCountry country = null;
        
        // Sanity checks
        // An IBAN can't be shorter than 5 characters, so do a premature exit if that is the case.
        if (iban.length() <= 5) {
            throw new IBANInvalidException(null);
        }
        
        // Detect any characters that aren't letters or numbers, automatically marking the IBAN as invalid.
        if (iban.matches(".*[^a-zA-Z0-9 ].*")) {
            throw new IBANInvalidException(null);
        }
        
        iban = iban.toUpperCase();
        iban = iban.replace(" ", "");
        country = validateCountry(iban);
        
        if (country == null) {
            return false;
        }
        
        IBANBuilder.append(iban.substring(4));
        IBANBuilder.append(iban.substring(0, 4));
        
        for (int i =0; i < IBANBuilder.length(); i ++) {
            char currentChar = IBANBuilder.charAt(i);
            if (currentChar >= 'A' && currentChar <= 'Z') {
                IBANBuilder.replace(i, i +1,
                                    Integer.toString(charToIbanDigit(currentChar)));
            }
        }
        
        IBANNumber = new BigInteger(IBANBuilder.toString());
        
        if (IBANNumber.remainder(new BigInteger("97")).equals(BigInteger.ONE)) {
            return true;
        }
        throw new IBANInvalidException(country);
    }
}
