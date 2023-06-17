package vault.voyage.app.panelFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import vault.voyage.app.R
import vault.voyage.app.model.User

class ProfileFragment(val user: User) : Fragment() {

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



        return view
    }
}