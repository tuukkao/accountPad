package net.tuukkao.accountPad;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A manager for loading, accessing and saving account data.
 * This class is a singleton and can't be used directly. Call {@link #getInstance} to get a reference.
 * <P>
 * All saved account data is loaded into memory upon first calling the getInstance method. Remember to call
 * {@link #save} after making changes to the account list.
 * <P>
 * To add or remove accounts, call {@link #getAccounts} and modify the list as necessary. Then call save.
 * 
 * @author Tuukka Ojala
 */
public final class AccountManager {
    private static AccountManager instance = null;
    private ArrayList<Account> accounts;
    private Gson gson;
    private Context context;
    /** Path for the serialized account list. */
    public final static  String ACCOUNT_LIST_PATH = "accounts.json";
    
    private AccountManager(Context context) {
        accounts = new ArrayList<Account>();
        gson = new Gson();
        this.context = context.getApplicationContext();
        load();
    }
    
    /**
     * Get the shared AccountManager instance.
     *
     * @param context The app's context.
     * @return The shared AccountManager instance.
     */
    public synchronized static AccountManager getInstance(Context context) {
        if (instance == null) {
            instance = new AccountManager(context);
        }
        
        return instance;
    }
    
    /**
     * Loads the account list into memory.
     * This method deserializes the file specified in {@link #ACCOUNT_LIST_PATH}. It is invoced automatically
     * when first getting a reference to this class.
     */
    private void load() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput(ACCOUNT_LIST_PATH)));
            Type collectionType = new TypeToken<ArrayList<Account>>(){}.getType();
            ArrayList<Account> accounts = gson.fromJson(reader, collectionType);
            this.accounts = accounts;
            reader.close();
        } catch (FileNotFoundException e) {
            // Doesn't matter; do nothing.
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Saves all account data.
     * This method serializes the list of {@link Account} objects to JSON and saves the result
     * to the file specified in {@link #ACCOUNT_LIST_PATH}.
     */
    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(ACCOUNT_LIST_PATH, context.MODE_PRIVATE)));
            writer.write(gson.toJson(accounts));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gets the list of all saved accounts.
     * 
     * @return an ArrayList containing all accounts this manager knows about.
     */
    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
