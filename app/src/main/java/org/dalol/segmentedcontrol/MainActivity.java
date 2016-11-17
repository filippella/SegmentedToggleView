package org.dalol.segmentedcontrol;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SegmentedToggleView.OnUIToggleCheckChangeListener {

    private ImageView mFruitImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFruitImageView = (ImageView) findViewById(R.id.fruit_image);

        List<String> selections = Arrays.asList("Apple", "Banana", "Orange", "Guava");

        SegmentedToggleView toggleView = (SegmentedToggleView) findViewById(R.id.segmented_control);
        toggleView.setListItems(selections);
        toggleView.setChangeListener(this);
        toggleView.setSelectedIndex(2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionAbout:
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("About Developer")
                        .setMessage("Developed by Filippo Engidashet on 11/17/2016!!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setCancelable(true)
                        .create();
                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckChanged(RadioButton button, int position) {
        String message = String.format("Selected fruit is %s on position %d", button.getText().toString(), position);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        int resId;

        switch (position) {
            case 0:
                resId = R.drawable.apple;
                break;
            case 1:
                resId = R.drawable.banana;
                break;
            case 2:
                resId = R.drawable.orange;
                break;
            case 3:
                resId = R.drawable.guava;
                break;
            default:
                resId = R.drawable.apple;
                break;
        }
        mFruitImageView.setImageResource(resId);
    }
}
