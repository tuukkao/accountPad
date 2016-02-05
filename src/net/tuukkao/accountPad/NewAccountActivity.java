package net.tuukkao.accountPad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import net.tuukkao.utils.dialogs.SimpleAlertDialogFragment;
import net.tuukkao.utils.ibanValidator.IBANCountry;
import net.tuukkao.utils.ibanValidator.IBANIncorrectLengthException;
import net.tuukkao.utils.ibanValidator.IBANInvalidException;
import net.tuukkao.utils.ibanValidator.IBANUnknownCountryCodeException;
import net.tuukkao.utils.ibanValidator.IBANValidator;

/**
 * An activity for inserting new accounts.
 *
 * @author Tuukka Ojala
 */
public class NewAccountActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account);
    }
    
    /**
     * Validates the entered information and saves it if possible.
     * This method checks if any of the text fields are empty. Then, it performs a validation check
     * for the provided IBAN. The account information will be saved if no errors are found;
     * the user will be informed of any errors.
     *
     * @param source The source the event originated from
     */
    public void save(View source) {
        EditText nameEdit = (EditText)findViewById(R.id.nameEdit);
        EditText IBANEdit = (EditText)findViewById(R.id.IBANEdit);
        String name = nameEdit.getText().toString();
        String iban = IBANEdit.getText().toString();
        AccountManager accountManager = AccountManager.getInstance(this);
        
        if (name.equals("") || iban.equals("")) {
            SimpleAlertDialogFragment.create(getString(R.string.empty_fields), getString(android.R.string.ok))
            .show(getFragmentManager(), "dialog");
            return;
        }
        
        try {
            if (IBANValidator.validate(iban)) {
                Account account = new Account(name, iban);
                accountManager.getAccounts().add(account);
                accountManager.save();
                finish();
            }
        } catch (IBANUnknownCountryCodeException e) {
            SimpleAlertDialogFragment.create(getString(R.string.unknown_country), getString(android.R.string.ok))
            .show(getFragmentManager(), "dialog");
        } catch (IBANIncorrectLengthException e) {
            IBANCountry country = e.getIBANCountry();
            String message = getString(R.string.incorrect_length).toString();
            message = String.format(message, country.getName(), country.getLength());
            SimpleAlertDialogFragment.create(message, getString(android.R.string.ok))
            .show(getFragmentManager(), "dialog");
        } catch (IBANInvalidException e) {
            SimpleAlertDialogFragment.create(getString(R.string.invalid_iban), getString(android.R.string.ok))
            .show(getFragmentManager(), "dialog");
        }
    }
}
