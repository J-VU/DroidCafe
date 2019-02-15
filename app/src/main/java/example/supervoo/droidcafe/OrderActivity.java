package example.supervoo.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Get the intent and its data.
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.order_text_view);
        // Change text view message to order
        textView.setText(message);

        // Creating the spinner
        Spinner spinner = findViewById(R.id.spinner_phone_type);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        // Create array adapter using string array for phone type (default layout)
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.array_phone_type, android.R.layout.simple_spinner_item);
        // Choosing a layout and the list will show
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        // Connecting the adapter to spinner
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), "Selected: " + message,
                Toast.LENGTH_SHORT).show();
    }

    // Display a Toast and *Process Data
    public void onRadioButtonClicked(View view) {
        // Check if button is clicked via boolean
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioButton_same_day:
                if (checked)
                    // Same day service
                    displayToast(getString(R.string.radio_label_same_day));
                break;
            case R.id.radioButton_next_day:
                if (checked)
                    // Next day delivery
                    displayToast(getString(R.string.radio_label_next_day));
                break;
            case R.id.radioButton_pick_up:
                if (checked)
                    // Pick up
                    displayToast(getString(R.string.radio_label_pick_up));
                break;
            default:
                // Do nothing.
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnerLabel = parent.getItemAtPosition(position).toString();
        displayToast(spinnerLabel);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Nothing happens
    }
}
