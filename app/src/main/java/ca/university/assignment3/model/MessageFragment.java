package ca.university.assignment3.model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import ca.university.assignment3.R;

// This fragment helps display a dialog box
// because this code is written with API 24
public class MessageFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // create view to show
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.message_layout, null);

        // a button listener
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        };

        // build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle("Congratulations!")
                .setMessage("You found all the gold Coins. Remember to share!!")
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();

    }
}
