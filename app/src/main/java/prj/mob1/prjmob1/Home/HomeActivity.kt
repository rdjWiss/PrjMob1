package prj.mob1.prjmob1.Home
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.SearchView
import android.view.Menu
import kotlinx.android.synthetic.main.movie_tab.*
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.initDrawer


class HomeActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setTitle("Home")

        apply {
            initDrawer()
            // The tab
            configureTabLayout()
        }
    }



    override fun onCreateOptionsMenu(menu: Menu) :Boolean{
        getMenuInflater().inflate(R.menu.search_item, menu)
        val mSearch = menu.findItem(R.id.action_search)
        val  mSearchView : SearchView =  mSearch.getActionView() as SearchView
        mSearchView.setQueryHint("Search")
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                //mAdapter.getFilter().filter(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }



    private fun configureTabLayout() {

        mv_tab_layout.addTab(mv_tab_layout.newTab().setText("Movies Playing"))
        mv_tab_layout.addTab(mv_tab_layout.newTab().setText("TV Shows airing"))

        val adapter = HomeTabPagerAdapter(supportFragmentManager,
                mv_tab_layout.tabCount)

        mv_viewpager.adapter = adapter
       mv_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(mv_tab_layout))

        mv_tab_layout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
               mv_viewpager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }


}

