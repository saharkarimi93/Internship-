package vault.voyage.app.panelFragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import vault.voyage.app.R
import vault.voyage.app.exceptions.InvalidEmailException
import vault.voyage.app.exceptions.InvalidPhoneNumber
import vault.voyage.app.model.EditActivator
import vault.voyage.app.model.EditStatus
import vault.voyage.app.model.User

class ProfileFragment(val user: User) : Fragment() {
    private var isEditUsername: EditActivator = EditActivator(false)
    private var isEditFirstname: EditActivator = EditActivator(false)
    private var isEditLastname: EditActivator = EditActivator(false)
    private var isEditEmail: EditActivator = EditActivator(false)
    private var isEditPassword: EditActivator = EditActivator(false)
    private var isEditNumber: EditActivator = EditActivator(false)

    private lateinit var username: EditText
    private lateinit var firstname: EditText
    private lateinit var lastname: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var number: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        username = view.findViewById(R.id.profile_username)
        firstname = view.findViewById(R.id.profile_firstname)
        lastname = view.findViewById(R.id.profile_lastname)
        email = view.findViewById(R.id.profile_email)
        password = view.findViewById(R.id.profile_password)
        number = view.findViewById(R.id.profile_number)

        setAllChanges()

        username.isEnabled = false
        firstname.isEnabled = false
        lastname.isEnabled = false
        number.isEnabled = false
        password.isEnabled = false
        email.isEnabled = false

        val editusername = view.findViewById<ImageButton>(R.id.profile_edit_username)
        val editPassword = view.findViewById<ImageButton>(R.id.profile_edit_password)
        val editfirstname = view.findViewById<ImageButton>(R.id.profile_edit_firstname)
        val editlastname = view.findViewById<ImageButton>(R.id.profile_editlastname)
        val editEmail = view.findViewById<ImageButton>(R.id.profile_edit_email)
        val editNumber = view.findViewById<ImageButton>(R.id.profile_edit_number)

        editusername.setOnClickListener(editTexts(isEditUsername, username, EditStatus.USERNAME))
        editPassword.setOnClickListener(editTexts(isEditPassword, password, EditStatus.PASSWORD))
        editfirstname.setOnClickListener(
            editTexts(
                isEditFirstname,
                firstname,
                EditStatus.FIRSTNAME
            )
        )
        editlastname.setOnClickListener(editTexts(isEditLastname, lastname, EditStatus.LASTNAME))
        editEmail.setOnClickListener(editTexts(isEditEmail, email, EditStatus.EMAIL))
        editNumber.setOnClickListener(editTexts(isEditNumber, number, EditStatus.NUMBER))

        return view
    }

    private fun setAllChanges() {
        username.setText(user.username)
        firstname.setText(user.firstname)
        lastname.setText(user.lastname)
        email.setText(user.email)
        number.setText(user.number)
        password.setText(user.password)
    }

    private fun editTexts(
        edit: EditActivator,
        editText: EditText,
        editStatus: EditStatus
    ): View.OnClickListener {
        val listener: View.OnClickListener = View.OnClickListener {
            if (edit.editActivated) {
                var exceptionThrown = false
                if (editText.text.isNotEmpty()) {
                    when (editStatus) {
                        EditStatus.USERNAME -> user.username = editText.text.toString()
                        EditStatus.EMAIL -> {
                            try {
                                user.setUserEmail(editText.text.toString())
                            } catch (ex: InvalidEmailException) {
                                makeDialog("Email is Invalid. Valid Form: example@example.com")
                                exceptionThrown = true
                            }
                        }

                        EditStatus.PASSWORD -> user.password = editText.text.toString()
                        EditStatus.FIRSTNAME -> user.firstname = editText.text.toString()
                        EditStatus.LASTNAME -> user.lastname = editText.text.toString()
                        EditStatus.NUMBER -> {
                            try {
                                user.setUserNumber(editText.text.toString())
                            } catch (ex: InvalidPhoneNumber) {
                                makeDialog("Number is Invalid. Valid Forms: +999999999999 or Any number between 7 to 14 character")
                                exceptionThrown = true
                            }
                        }

                        else -> {}
                    }
                    setAllChanges()
                    edit.editActivated = !edit.editActivated
                    editText.isEnabled = false;
                    if (!exceptionThrown) {
                        Toast.makeText(context, "$editStatus Edited", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    editText.isEnabled = false
                    edit.editActivated = !edit.editActivated
                    setAllChanges()
                }
            } else {
                editText.isEnabled = true
                edit.editActivated = true
                Toast.makeText(context, "Edit $editStatus Activated", Toast.LENGTH_SHORT).show()
            }
        }

        return listener
    }

    private fun makeDialog(dialogText: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setMessage(dialogText)
        builder.setTitle("Error")
        builder.setNegativeButton("Ok") { dialog, which ->
            dialog.cancel()
        }
        val dialog = builder.create()
        dialog.show()
    }
}