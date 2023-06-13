package vault.voyage.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import vault.voyage.app.model.User
import vault.voyage.app.panelFragments.ProfileFragment
import vault.voyage.app.panelFragments.selectedItemsFragment.SelectedItemsFragment
import vault.voyage.app.panelFragments.todoFragment.TodoFragment
import vault.voyage.app.panelFragments.bagFragment.BagFragment

class PanelActivity : AppCompatActivity() {
    companion object{
        @JvmStatic val user = User("sahar karimi","saharkarimi@gmail.com","+123456789")
    }
    private val profile = ProfileFragment(user)
    private val bag = BagFragment(user)
    private val todo = TodoFragment(user)
    private val selectedItems = SelectedItemsFragment(user)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.panel_activity)


        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        switchFragment(bag)
        bottom_nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.mybag_menuItem -> switchFragment(selectedItems)
                R.id.todo_menuItem -> switchFragment(todo)
                R.id.backpack_menuItem->switchFragment(bag)
                R.id.profile_menuItem->switchFragment(profile)
                else -> {false}
            }
        }
    }
    fun switchFragment(fragment:Fragment):Boolean{
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.panels_container,fragment)
            .commit()
        return true

    }
}