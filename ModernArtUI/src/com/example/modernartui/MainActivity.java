package com.example.modernartui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

	// Identifier for "more information" Dialog
	private static final int MORE_INFO_TAG = 0;

	private SeekBar seekBar;

	private Map<TextView, Integer> textViewColorsMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// make seekbar object
		seekBar = (SeekBar) findViewById(R.id.seekBar1);

		// set seekbar listener.
		// since we are using this class as the listener the
		// class is "this"
		seekBar.setOnSeekBarChangeListener(this);

		initializeTextViewColorMap();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.more_information) {
			showDialogFragment(MORE_INFO_TAG);
		}
		return super.onOptionsItemSelected(item);
	}

	// Show desired Dialog
	private void showDialogFragment(int dialogID) {

		switch (dialogID) {

		// Show AlertDialog
		case MORE_INFO_TAG:

			// Show AlertDialogFragment
			MoreInfoDialogFragment.newInstance().show(getFragmentManager(),
					"Alert");

			break;
		}
	}
	
	private void initializeTextViewColorMap() {
		textViewColorsMap = new HashMap<TextView, Integer>();

		TextView textView1 = (TextView) findViewById(R.id.textView1);
		int color1 = ((ColorDrawable) textView1.getBackground()).getColor();

		TextView textView2 = (TextView) findViewById(R.id.textView2);
		int color2 = ((ColorDrawable) textView2.getBackground()).getColor();

		TextView textView3 = (TextView) findViewById(R.id.textView3);
		int color3 = ((ColorDrawable) textView3.getBackground()).getColor();

		TextView textView4 = (TextView) findViewById(R.id.textView4);
		int color4 = ((ColorDrawable) textView4.getBackground()).getColor();

		TextView textView5 = (TextView) findViewById(R.id.textView5);
		int color5 = ((ColorDrawable) textView5.getBackground()).getColor();

		textViewColorsMap.put(textView1, color1);
		textViewColorsMap.put(textView2, color2);
		textViewColorsMap.put(textView3, color3);
		textViewColorsMap.put(textView4, color4);
		textViewColorsMap.put(textView5, color5);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

		for (Entry<TextView, Integer> entry : textViewColorsMap.entrySet()) {
			int color = (Integer) entry.getValue();
			if (color != Color.WHITE) {
				TextView tv = entry.getKey();
				tv.setBackgroundColor(color + progress);
			}
		}

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}

	// Class that creates the MoreInfoDialog
	public static class MoreInfoDialogFragment extends DialogFragment {

		public static MoreInfoDialogFragment newInstance() {
			return new MoreInfoDialogFragment();
		}

		// Build MoreInfoDialogFragment using AlertDialog.Builder
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return new AlertDialog.Builder(getActivity())
					.setMessage(
							"Inspired by the work of artists such as Vlad Mirel"
									+ "\n\nClick below to learn more\n")

					// User cannot dismiss dialog by hitting back button
					.setCancelable(false)

					// Set up No Button
					.setNegativeButton("Not now",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							})

					// Set up Yes Button
					.setPositiveButton("Visit MOMA",
							new DialogInterface.OnClickListener() {
								public void onClick(
										final DialogInterface dialog, int id) {

									Intent momaIntent = new Intent(
											android.content.Intent.ACTION_VIEW,
											Uri.parse("https://www.coursera.org/moma"));

									startActivity(momaIntent);
								}
							}).create();
		}
	}
}
