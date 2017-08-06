package a84b9cb.info.guchiru

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

class MainListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)

        val fragment = MainListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment as Fragment)
        transaction.commit()
    }
}
