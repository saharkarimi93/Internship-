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
import vault.voyage.app.panelFragments.nearmeFragment.NearMeFragment

class PanelActivity : AppCompatActivity() {
    companion object{
        @JvmStatic
        lateinit var user: User
    }
    private lateinit var profile:ProfileFragment
    private lateinit var bag:BagFragment
    private lateinit var todo :TodoFragment
    private lateinit var selectedItems:SelectedItemsFragment
    private lateinit var nearMe:NearMeFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.panel_activity)


        profile = ProfileFragment(user)
        bag = BagFragment(user)
        todo = TodoFragment(user)
        selectedItems = SelectedItemsFragment(user)
        nearMe = NearMeFragment(this,user)

        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        switchFragment(bag)
        bottom_nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.backpack_menuItem->switchFragment(bag)
                R.id.todo_menuItem -> switchFragment(todo)
                R.id.mybag_menuItem -> switchFragment(selectedItems)
                R.id.profile_menuItem->switchFragment(profile)
                R.id.nearMe_menuItem -> switchFragment(nearMe)
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