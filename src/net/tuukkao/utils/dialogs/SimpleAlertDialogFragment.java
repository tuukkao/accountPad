package net.tuukkao.utils.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * A dialog fragment for showing a simple alert dialog with a single button.
 *
 * @author Tuukka Ojala
 */
public class SimpleAlertDialogFragment extends DialogFragment {
    /**
     * Creates a new instance of the fragment with the specified parameters.
     *
     * @param message The message that is displayed on the dialog.
     * @param okButtonText The text of the "ok" button.
     * @return The created SimpleAlertDialogFragment instance.
     */
    public static SimpleAlertDialogFragment create(CharSequence message, CharSequence okButtonText) {
        SimpleAlertDialogFragment fragment = new SimpleAlertDialogFragment();
        Bundle arguments = new Bundle();
        arguments.putCharSequence("message", message);
        arguments.putCharSequence("okButtonText", okButtonText);
        fragment.setArguments(arguments);
        return fragment;
    }
    
    /**
     * Creates the dialog.
     *
     * @param savedInstanceState savedInstanceState
     * @return A Dialog instance.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        return new AlertDialog.Builder(getActivity())
        .setCancelable(false)
        .setMessage(arguments.getCharSequence("message"))
        .setPositiveButton(arguments.getCharSequence("okButtonText"), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        })
        .create();
    }
}
