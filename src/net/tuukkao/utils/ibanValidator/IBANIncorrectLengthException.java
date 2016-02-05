package net.tuukkao.utils.ibanValidator;

/**
 * Thrown if the provided IBAN isn't the right length for the country's standard.
 *
 * @author Tuukka Ojala
 */
public class IBANIncorrectLengthException extends IBANException {
    public IBANIncorrectLengthException(IBANCountry ibanCountry) {
        super(ibanCountry);
    }
}
