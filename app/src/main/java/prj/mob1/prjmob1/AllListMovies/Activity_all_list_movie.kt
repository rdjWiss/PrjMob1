package prj.mob1.prjmob1.AllListMovies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.ConnectivityChecker
import prj.mob1.prjmob1.Util.addFragment
import prj.mob1.prjmob1.Util.initDrawer

class Activity_all_list_movie : AppCompatActivity() {


    val allListMoviesFragment=AllListMoviesFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_list_movie)
        setTitle("All Movies")

        /*if(!ConnectivityChecker.isNetworkAvailable(this)){
            Toast.makeText(this, "No Network Connection", Toast.LENGTH_LONG).show()

        }*/
        apply {
            initDrawer()
           //addFragment(AllListMoviesFragment(),R.id.container_body_all_movie)
            addFragment( allListMoviesFragment,R.id.container_body_all_movie)
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
                allListMoviesFragment.onQueryTextChange(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}
