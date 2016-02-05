package net.tuukkao.accountPad;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * An activity for managing accounts.
 * This activiy displays a list of any added accounts as well as an action bar
 * where a new account can be added.
 *
 * @author Tuukka Ojala
 */
public class AccountListActivity extends ListActivity {
    private ArrayAdapter<Account> accountListAdapter;
    private AccountManager accountManager;
    private ListView accountList;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountList = getListView();
        accountManager = AccountManager.getInstance(this);
        accountListAdapter = new ArrayAdapter<Account>(this, android.R.layout.simple_list_item_1, accountManager.getAccounts());
        setListAdapter(accountListAdapter);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        accountListAdapter.notifyDataSetChanged();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
          case R.id.addAccountAction:
            Intent intent = new Intent(this, NewAccountActivity.class);
            startActivity(intent);
            return true;
          default:
            return false;
        }
    }
}
