package vault.voyage.app.panelFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import vault.voyage.app.R
import vault.voyage.app.model.EditActivator
import vault.voyage.app.model.EditStatus
import vault.voyage.app.model.User

class ProfileFragment(val user: User) : Fragment() {
    private var isEditUsername:EditActivator = EditActivator(false)
    private var isEditFirstname:EditActivator = EditActivator(false)
    private var isEditLastname:EditActivator = EditActivator(false)
    private var isEditEmail:EditActivator = EditActivator(false)
    private var isEditPassword:EditActivator = EditActivator(false)
    private var usEditNumber:EditActivator = EditActivator(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val username = view.findViewById<EditText>(R.id.profile_username)
        val firstname = view.findViewById<EditText>(R.id.profile_firstname)
        val lastname = view.findViewById<EditText>(R.id.profile_lastname)
        val email = view.findViewById<EditText>(R.id.profile_email)
        val password = view.findViewById<EditText>(R.id.profile_password)
        val number = view.findViewById<EditText>(R.id.profile_number)
        username.setText(user.username)
        firstname.setText(user.firstname)
        lastname.setText(user.lastname)
        email.setText(user.email)
        number.setText(user.number)
        password.setText(user.password)

        username.isEnabled = false
        firstname.isEnabled = false
        lastname.isEnabled = false
        number.isEnabled = false
        password.isEnabled = false
        email.isEnabled = false

        val editusername = view.findViewById<ImageButton>(R.id.profile_edit_username)
        val editpasstowd = view.findViewById<ImageButton>(R.id.profile_edit_password)
        val editfirstname = view.findViewById<ImageButton>(R.id.profile_edit_firstname)
        val editlastname = view.findViewById<ImageButton>(R.id.profile_editlastname)
        val editEmail = view.findViewById<ImageButton>(R.id.profile_edit_email)
        val editNumber= view.findViewById<ImageButton>(R.id.profile_edit_number)

        editusername.setOnClickListener(editTexts(isEditUsername,username,EditStatus.USERNAME))

        username.setText(user.username)
        firstname.setText(user.firstname)
        lastname.setText(user.lastname)
        email.setText(user.email)
        number.setText(user.number)
        password.setText(user.password)

        return view
    }

    private fun editTexts( edit:EditActivator,editText:EditText,editStatus:EditStatus):View.OnClickListener{

        val listener:View.OnClickListener = View.OnClickListener {
            editText.isEnabled = true
            if(edit.editActivated){
                if(editText.text.isNotEmpty()){
                    when(editStatus){
                        EditStatus.USERNAME-> user.username = editText.text.toString()
                        EditStatus.EMAIL-> user.email = editText.text.toString()
                        EditStatus.PASSWORD-> user.password = editText.text.toString()
                        EditStatus.FIRSTNAME-> user.firstname = editText.text.toString()
                        EditStatus.LASTNAME-> user.lastname = editText.text.toString()
                        EditStatus.NUMBER ->user.number = editText.text.toString()
                        else -> {}
                    }
                    edit.editActivated = !edit.editActivated
                    editText.isEnabled = false;
                    Toast.makeText(context, "$editStatus Edited", Toast.LENGTH_SHORT).show()
                }else{
                    edit.editActivated = false
                    Toast.makeText(context, "Edit $editStatus Activated", Toast.LENGTH_SHORT).show()
                }

            }
        }


        return listener
    }
}