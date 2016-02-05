package net.tuukkao.utils.ibanValidator;

/**
 * Thrown if the provided IBAN's country code is unknown.
 *
 * Here, unknown means that it is not one of the constants specified in {@link IBANCountry}.
 * This can mean either an invalid country code or a recent country that isn't yet supported by the library.
 *
 * @author Tuukka Ojala
 */
public class IBANUnknownCountryCodeException extends IBANException {
    public IBANUnknownCountryCodeException(IBANCountry ibanCountry) {
        super(ibanCountry);
    }
}

