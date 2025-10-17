package com.example.employeeapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    //The URL used is https://web.njit.edu/~halper/it114/l2d.txt

    public void displayEmployees(View view) throws IOException {

        TextView tv;
        EditText et1;
        String inURL;
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        Employee employee3 = new Employee();

        tv = (TextView) findViewById(R.id.text_main);
        tv.setText("");

        et1 = (EditText) findViewById(R.id.edit_file);

        inURL = et1.getText().toString();


        URL file_url = new URL(inURL);
        Scanner fsc = new Scanner(file_url.openStream());

        //Putting the employee information from the web file onto the 3 employee objects
        for (int i = 0; i < 4; i++){
            if (i == 1){
                addEmployee(employee1, fsc, tv);
            }
            else if (i == 2){
                addEmployee(employee2, fsc, tv);
            }
            else if (i == 3){
                addEmployee(employee3, fsc, tv);
            }

        }

        //Average Years of Service
        float avgOfYears = (float) Math.round(((float) (employee1.yearsOfService + employee3.yearsOfService + employee2.yearsOfService) / 3) * 10) /10;

        //Geometric average of salaries
        float geoAvgSal = (float) Math.round( (float) Math.cbrt(employee1.salary * employee2.salary * employee3.salary) * 100) / 100;
        tv.append("Average Years: " + avgOfYears + "\n" + "Geometric Average of Salaries: " + geoAvgSal + "\n");



    }
    public void addEmployee(Employee employee,Scanner file, TextView list){
        employee.name = file.nextLine();
        employee.id = file.nextLine();
        employee.salary = file.nextDouble();
        file.nextLine();
        employee.office = file.nextLine();
        employee.extension = file.nextLine();
        employee.yearsOfService = file.nextInt();
        file.nextLine();
        list.append(employee.name + "\n" + employee.id + "\n" + employee.salary + "\n" + employee.office + "\n" + employee.extension + "\n" + employee.yearsOfService + "\n" + "\n" );
    }



}

