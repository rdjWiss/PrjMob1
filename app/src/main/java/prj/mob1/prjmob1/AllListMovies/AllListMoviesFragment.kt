package prj.mob1.prjmob1.AllListMovies

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import prj.mob1.prjmob1.ListItem.*
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.R.layout.item
import prj.mob1.prjmob1.movie.MovieActivity
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService
import retrofit2.Response


/**
 * Created by LE on 17/04/2018.
 */
class AllListMoviesFragment: BaseFragment_New()
{
    private lateinit var list_adapter : MyListAdapter
    private var  mRecyclerView: RecyclerView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view!!)
        getData()
    }

    private fun initRecyclerView(view : View) {
        mRecyclerView = view.findViewById<View>(R.id.item_listview) as RecyclerView
        mRecyclerView?.setHasFixedSize(true)
        if (resources.getString(R.string.isLand) == "false")
            mRecyclerView!!.layoutManager = GridLayoutManager(context as AppCompatActivity,2)
        else
            mRecyclerView!!.layoutManager = GridLayoutManager(activity,4)
        mRecyclerView!!.addOnItemTouchListener(BaseFragment.RecyclerTouchListener(activity!!.applicationContext,  mRecyclerView!!, object : BaseFragment.ClickListener {
            override fun onClick(view: View, position: Int) {
                openActivity(position)
            }
            override fun onLongClick(view: View?, position: Int) {
            }
        }))
    }

    override fun openActivity(position:Int)
    {
        val intent = Intent(context, MovieActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("id",list_adapter.arrayList[position].id)
        intent.putExtra("bundle",bundle)
        startActivity(intent)
    }



    override fun getData(){
        RemoteApiService.apply { sendRequest(create()!!.getAllMovies(), { onCreateMovieDataSuccess(it) },{onCreateMovieLatestFail(it)}) }
    }

    fun onCreateMovieDataSuccess(result: Response<ListMovies>)
    {
        if (result.isSuccessful) {
            val movies_now =ArrayList<Item>()
            for (movie  in result.body()!!.movies) {
               // var item = Item(movie.id,movie.posterId, movie.year, movie.title, movie.tags)
                var item = Item(movie.id,movie.posterId)
                movies_now.add(item)
            }
            list_adapter= MyListAdapter(context as AppCompatActivity,movies_now )
            mRecyclerView?.adapter= list_adapter

        } else //error 400-500
            Log.e("erroor","err" +result.body().toString())
    }
   /* fun onCreateMovieLatestFail(error:Throwable) {
        Log.e("erroor","errror"+ error.message.toString())
    }*/

}