package net.tuukkao.utils.ibanValidator;

/** 
 Thrown if the provided IBAN doesn't pass the built-in validation check.
 *
 * In practice, this would mean that one of more characters have been mistyped.
 *
 * @author Tuukka Ojala
 */
public class IBANInvalidException extends IBANException {
    public IBANInvalidException(IBANCountry ibanCountry) {
        super(ibanCountry);
    }
}
