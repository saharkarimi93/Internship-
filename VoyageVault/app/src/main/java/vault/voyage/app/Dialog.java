package vault.voyage.app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Dialog extends AppCompatDialogFragment {
    private EditText title;
    private EditText description;
    public DialogListener dialogListener;
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_todo,null);
        builder.setView(view)
                .setTitle("Add Task")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add Task", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title_temp = title.getText().toString();
                        String desc_temp = description.getText().toString();
                        if (!title_temp.trim().isEmpty() && !desc_temp.trim().isEmpty()) {
                            dialogListener.sendData(title_temp,desc_temp);
                        }
                    }
                });
        title = view.findViewById(R.id.task_title_dialog);
        description = view.findViewById(R.id.task_desc_dialog);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogListener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Please Implement DialogListener");
        }
    }

    public interface DialogListener{
        void sendData(String title,String description);
    }
}
