package com.example.roomtutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Delete;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.roomtutorial.database.CustomerDatabase;
import com.example.roomtutorial.entity.Customer;
import com.example.roomtutorial.viewmodel.CustomerViewModel;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CustomerDatabase db = null;
    EditText editText = null;
    TextView textView_insert = null;
    TextView textView_read = null;
    TextView textView_delete = null;
    TextView textView_update = null;
    CustomerViewModel customerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //db = CustomerDatabase.getInstance(this);

        editText = findViewById(R.id.editText);

        Button addButton = findViewById(R.id.addButton);
        textView_insert = findViewById(R.id.textView_add);

        //Button readButton = findViewById(R.id.readButton);
        textView_read = findViewById(R.id.textView_read);

        final Button deleteButton = findViewById(R.id.deleteButton);
        textView_delete = findViewById(R.id.textView_delete);

        Button updateButton = findViewById(R.id.updateButton);
        textView_update = findViewById(R.id.textView_update);

        customerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
        customerViewModel.initializeVars(getApplication());
        customerViewModel.getAllCustomers().observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                String allCustomers = "";
                for (Customer temp: customers) {
                    String customerStr = (temp.getId() + " " + temp.getFirstName() + " " + temp.getLastName() + " " + temp.getSalary());
                    allCustomers = allCustomers + System.getProperty("line.separator") + customerStr;
                }
                textView_read.setText("All data: " + allCustomers);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(editText.getText().toString().trim().isEmpty())) {
                    String[] details = editText.getText().toString().trim().split(" ");
                    if (details.length == 3) {
                        /*
                        InsertDatabase addDatabase = new InsertDatabase();
                        addDatabase.execute(details);

                         */
                        Customer customer = new Customer(details[0],details[1],Double.parseDouble(details[2]));
                        customerViewModel.insert(customer);
                        textView_insert.setText("Added Record: " + Arrays.toString(details));
                    }
                }
            }
        });

        /*
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadDatabase readDatabase = new ReadDatabase();
                readDatabase.execute();
            }
        });

         */

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                DeleteDatabase deleteDatabase = new DeleteDatabase();
                deleteDatabase.execute();

                 */
                customerViewModel.deleteAll();
                textView_delete.setText("All data was deleted");
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String[] details = editText.getText().toString().trim().split(" ");
                if (details.length == 4) {
                    /*
                    UpdateDatabase updateDatabase = new UpdateDatabase();
                    updateDatabase.execute(details);

                     */
                    Customer customer = null;
                    int id = Integer.parseInt(details[0]);
                    customer = customerViewModel.findByID(id);
                    if (customer != null) {
                        customer.setFirstName(details[1]);
                        customer.setLastName(details[2]);
                        customer.setSalary(new Double(details[3]).doubleValue());
                        customerViewModel.update(customer);
                        textView_update.setText("Update details: " + Arrays.toString(details));
                    }
                }
            }
        });

    }

    /*
    private class InsertDatabase extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            Customer customer = new Customer(strings[0], strings[1], Double.parseDouble(strings[2]));
            long id = db.customerDao().insert(customer);
            return (id + " " + strings[0] + " " + strings[1] + " " + strings[2]);
        }

        @Override
        protected void onPostExecute(String s) {
            textView_insert.setText("Added Record: " + s);
        }
    }

    private class ReadDatabase extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            List<Customer> users = db.customerDao().getAll();
            if (!(users.isEmpty() || users == null)) {
                String allUsers = "";
                for (Customer temp: users) {
                    String userStr = (temp.getId() + " " + temp.getFirstName() + " " + temp.getLastName() + " " + temp.getSalary() + ", ");
                    allUsers = allUsers + System.getProperty("line.separator") + userStr;
                }
                return allUsers;
            } else {
                return "";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            textView_read.setText("All data: " + s);
        }
    }

    private class DeleteDatabase extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            db.customerDao().deleteAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            textView_delete.setText("All data was deleted");
        }
    }

    private class UpdateDatabase extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            Customer user = null;
            int id = Integer.parseInt(strings[0]);
            user = db.customerDao().findByID(id);
            if (user!=null) {
                user.setFirstName(strings[1]);
                user.setLastName(strings[2]);
                user.setSalary(new Double(strings[3]).doubleValue());

                db.customerDao().updateCustomers(user);
                return (strings[0] + " " + strings[1] + " " + strings[2] + " " + strings[3]);
            } else {
                return "";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            textView_update.setText("Updated details: " + s);
        }
    }

     */
}
