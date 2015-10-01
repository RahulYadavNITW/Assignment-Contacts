package com.thenewboston.assignment_contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements ListView_Interface{
    Toolbar toolbar;
    LoadingDialogFragment dialogFragment = (LoadingDialogFragment) getFragmentManager().findFragmentByTag(LoadingDialogFragment.FRAGMENT_TAG);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView t = (TextView) findViewById(R.id.toolbar_textview);
        t.setText("CONTACTS");
        TextView aplhabet =(TextView) findViewById(R.id.toolbar_alphabet);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                    .add(R.id.leftContainer,new LeftFragment())
                    .commit();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rightContainer, new RightFragment())
                    .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   public void setActionBarTitle(String title)
   {
       Log.d("Action bar",title);
       toolbar = (Toolbar) findViewById(R.id.toolbar);
       TextView t = (TextView) findViewById(R.id.toolbar_textview);
       t.setText(title);
       return;
   }

    public void changeListPosition(String letter) {
        RightFragment rightfragment = (RightFragment) getSupportFragmentManager().findFragmentById(R.id.rightContainer);

        if (rightfragment !=null)
        {
            rightfragment.changePosition(letter);
        }
    }

    public void searchContacts(final String query) {
        final RightFragment contactsFragment = (RightFragment) getSupportFragmentManager().findFragmentById(R.id.rightContainer);

        if (contactsFragment !=null)
        {
            contactsFragment.searchContactsRightFragment(query);

        }
    }

    @Override
    public void loaddialog() {
        if (dialogFragment == null) {
            dialogFragment = new LoadingDialogFragment();
            dialogFragment.setCancelable(false);
            getFragmentManager().beginTransaction()
                    .replace(R.id.dialogfragment, dialogFragment)
                    .commit();
            // dialogFragment.show(getSupportFragmentManager().beginTransaction(), LoadingDialogFragment.FRAGMENT_TAG);
        }
    }

    @Override
    public void stopdialog() {
        if (dialogFragment != null) {
            // dialogFragment.dismissAllowingStateLoss();
            getFragmentManager().beginTransaction().remove(dialogFragment).commitAllowingStateLoss();
        }

    }

}
