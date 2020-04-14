package com.example.lab_32;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private  static  final  double  P  =  4.0 ;
    private static final int[][] points = {{0, 6}, {1, 5}, {3, 3}, {2, 4}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super . onCreate (savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v) {
        final double speedOfLearning = Double.parseDouble(((Spinner) findViewById(R.id.spinner)).getSelectedItem().toString());
        final double timeOfDeadline = Double.parseDouble(((Spinner) findViewById(R.id.spinner2)).getSelectedItem().toString());
        final int numberOfIterations = Integer.parseInt(((Spinner) findViewById(R.id.spinner3)).getSelectedItem().toString());

        double w1 = 0;
        double w2 = 0;
        double y;
        double delta;
        int currentIteration = 0;
        boolean flag = false;
        long start =  System.nanoTime ();

        int i =  - 1 ;
        do {
            if (i == points.length - 1) {
                i = -1;
            }
            i++;
            currentIteration++;

            y = points[i][0] * w1 + points[i][1] * w2;

            if (isSatisfy(w1, w2)) {
                flag = true;
                break;
            }

            delta =  P  - y;
            w1 += delta * points[i][0] * speedOfLearning;
            w2 += delta * points[i][1] * speedOfLearning;
        } while (currentIteration < numberOfIterations
                && ( System .nanoTime() - start) / 1_000_000_000.0  < timeOfDeadline);

        TextView result = findViewById(R.id.result);
        if (flag) {
            result.setText("w1 = " + w1 + "\nw2 = " + w2);
        } else {
            result . setText ( " Deadline " );
        }
    }

    private boolean isSatisfy(double w1, double w2) {
        return P < points[0][0] * w1 + points[0][1] * w2
                && P < points[1][0] * w1 + points[1][1] * w2
                && P > points[2][0] * w1 + points[2][1] * w2
                && P > points[3][0] * w1 + points[3][1] * w2;
    }
}