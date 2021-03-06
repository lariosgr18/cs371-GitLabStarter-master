package edu.up.cs371.textmod;

/**
 * class TextModActivity
 *
 * Allow text to be modified in simple ways with button-presses.
 */
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import java.util.ArrayList;

public class TextModActivity extends ActionBarActivity {

    // array-list that contains our images to display
    private ArrayList<Bitmap> images;

    // instance variables containing widgets
    private ImageView imageView; // the view that shows the image
    private EditText edit;
    private EditText editText; //the view that shows the edittext
    //instance variable containing text
    private EditText text;
    private Spinner spinner;
    private Button remove;

    /**
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // perform superclass initialization; load the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_mod);

        edit = (EditText) findViewById(R.id.editText);
        // set instance variables for our widgets
        imageView = (ImageView) findViewById(R.id.imageView);
        editText = (EditText) findViewById(R.id.editText);

        remove = (Button) findViewById(R.id.remove) ;

        // Set up the spinner so that it shows the names in the spinner array resources
        //fdgdgd
        // get spinner object
        spinner = (Spinner) findViewById(R.id.spinner);
        // get array of strings
        String[] spinnerNames = getResources().getStringArray(R.array.spinner_names);
        // create adapter with the strings
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, spinnerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // bind the spinner and adapter
        spinner.setAdapter(adapter);
        //gets edittext
        text = (EditText) findViewById(R.id.editText);

        // load the images from the resources
        //
        // create the arraylist to hold the images
        images = new ArrayList<Bitmap>();
        // get array of image-resource IDs
        TypedArray imageIds2 = getResources().obtainTypedArray(R.array.imageIdArray);
        // loop through, adding one image per string
        for (int i = 0; i < spinnerNames.length; i++) {
            // determine the index; use 0 if out of bounds
            int id = imageIds2.getResourceId(i, 0);
            if (id == 0) id = imageIds2.getResourceId(0, 0);
            // load the image; add to arraylist
            Bitmap img = BitmapFactory.decodeResource(getResources(), id);
            images.add(img);


        }

        // define a listener for the spinner
        spinner.setOnItemSelectedListener(new MySpinnerListener());

    }

    /**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_mod, menu);
        return true;
    }

    /**
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
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

    public void clear(View view) {
        edit.setText("");
    }

    public void upper(View view) {
        edit.setText(edit.getText().toString().toUpperCase());
    }

    public void lower(View view) {
        edit.setText(edit.getText().toString().toLowerCase());
    }


    /**
     * class that handles our spinner's selection events
     */
    private class MySpinnerListener implements OnItemSelectedListener {

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(
         *android.widget.AdapterView, android.view.View, int, long)
         */
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                   int position, long id) {
            // set the image to the one corresponding to the index selected by the spinner
            imageView.setImageBitmap(images.get(position));
        }

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(
         *android.widget.AdapterView)
         */
        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // your code herge
        }
    }

    public void copy(View v) {

        text.setText(text.getText() + spinner.getSelectedItem().toString());
    }

    public void reverse(View view) {
        StringBuffer a = new StringBuffer(editText.getText());
        editText.setText(a.reverse());
    }

    public void alternate(View v) {
        String oldString = editText.getText().toString().toLowerCase();
        String newString = "";
        boolean nextCapital = true;
        for (int i = 0; i < oldString.length(); i++) {
            char currentChar = oldString.charAt(i);
            if (Character.isLetter(currentChar)) {
                if (nextCapital) {
                    newString += Character.toUpperCase(currentChar);
                    nextCapital = false;
                } else {
                    newString += Character.toLowerCase(currentChar);
                    nextCapital = true;
                }
            } else {
                newString += currentChar;
            }
            editText.setText(newString);
        }


    }

    public void deleteSpace(View view)
    {
        String remove = edit.getText().toString();
        remove = remove.replaceAll(" ","");
        edit.setText(remove);
        //comment
    }

    public void remove(View v){

        String input = text.getText().toString();
        Character f;
        int random= (int) (Math.random()*input.length());
        String output = input.substring(0,random);
        String rest = input.substring(random,input.length());
        String newString = output + random +rest;
        editText.setText(newString);

    }
}
