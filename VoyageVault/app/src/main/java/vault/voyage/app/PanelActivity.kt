package vault.voyage.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import vault.voyage.app.R
import vault.voyage.app.panelFragments.ProfileFragment
import vault.voyage.app.panelFragments.SelectedItemsFragment
import vault.voyage.app.panelFragments.TodoFragment
import vault.voyage.app.panelFragments.bagFragment.BagFragment

class PanelActivity : AppCompatActivity() {
    private val profile = ProfileFragment()
    private val bag = BagFragment()
    private val todo = TodoFragment()
    private val selectedItems = SelectedItemsFragment()
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