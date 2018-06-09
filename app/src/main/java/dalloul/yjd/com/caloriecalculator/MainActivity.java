package dalloul.yjd.com.caloriecalculator;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner spin;
    EditText editText_age, editText_weight, editText_height;
    TextView textView;
    Button button;
    int  vastaus = 0;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin=findViewById(R.id.spinner1);
        editText_age = findViewById(R.id.editText_a);
        editText_weight = findViewById(R.id.editText_w);
        editText_height = findViewById(R.id.editText_h);

        textView = findViewById(R.id.textView_ll);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        button=findViewById(R.id.btn_go);
        button.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                int age = Integer.parseInt(editText_age.getText().toString().trim());
                int weight = Integer.parseInt(editText_weight.getText().toString().trim());
                int height = Integer.parseInt(editText_height.getText().toString().trim());

                String test1, test2, test3;
                test1 = getString(R.id.editText_a);
                test2 = getString(R.id.editText_w);
                test3 = getString(R.id.editText_h);

                try {
                    if (test1 != "" && test2 != "" && test3 != "") {

                        if(gender.contains("Male"))
                            vastaus = (int) Math.round(1.2 * (66 + (13.7 * weight) + (5 * height) - (6.8 * age)));

                        if(gender.contains("Female"))
                            vastaus = (int) Math.round(1.2*(655 + (9.6 * weight) + (1.8 * height) - (4.7 * age)));

                        editText_age.setText("");
                        editText_weight.setText("");
                        editText_height.setText("");

                        textView.setText("Your caloric consumption is:"+vastaus + " kcal/day");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

       spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               gender = parent.getItemAtPosition(position).toString();

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

    }
}
