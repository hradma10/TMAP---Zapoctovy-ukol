package cz.tmap.converterApp.fragmentsAndActivities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import cz.tmap.converter.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var screenSlidePagerAdapter: ScreenSlidePagerAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_pager)

        viewPager = findViewById(R.id.main_pager)
        tabLayout = findViewById(R.id.tab_layout)
        screenSlidePagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = screenSlidePagerAdapter

        addFragmentsToAdapter()

        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = screenSlidePagerAdapter.getTabTitle(position)
        }
        tabLayoutMediator.attach()
    }

    private fun addFragmentsToAdapter() {
        screenSlidePagerAdapter.addFragment(CalculatorFragment(), getString(R.string.calc_tab))
        screenSlidePagerAdapter.addFragment(ListOfConvFragment(), getString(R.string.conv_tab))
        screenSlidePagerAdapter.addFragment(HistoryFragment(), getString(R.string.history_tab))
    }
}