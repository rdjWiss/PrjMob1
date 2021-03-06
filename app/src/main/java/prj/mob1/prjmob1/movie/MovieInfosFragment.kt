package prj.mob1.prjmob1.movie

import android.content.Context
import android.graphics.Movie
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_infos.*
import prj.mob1.prjmob1.ActionsInterface
import prj.mob1.prjmob1.R

import prj.mob1.prjmob1.databinding.FragmentMovieInfosBinding
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService

/**
 * Created by sol on 25/03/2018.
 */
class MovieInfosFragment: Fragment() {

    private lateinit var listener: ActionsInterface

    private lateinit var movie: MovieClass
    private var movieInFav = false

    companion object {

        private val ARG_movie = "Movie"
        private val ARG_FAV = "fav"

        fun newInstance(movie: MovieClass, movieInFav: Boolean): MovieInfosFragment {
            val fragment = MovieInfosFragment()
            val args = Bundle()
            args.putParcelable(ARG_movie, movie)
            args.putBoolean(ARG_FAV,movieInFav)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            movie = arguments!!.getParcelable(ARG_movie)
            movieInFav= arguments!!.getBoolean(ARG_FAV)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is ActionsInterface) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement an interface implementing ActionsInterface.")
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : FragmentMovieInfosBinding =
                FragmentMovieInfosBinding.inflate(inflater ,container , false)
        val myView : View  = binding.root

        binding.movie = movie

        val poster = myView.findViewById<ImageView>(R.id.movie_infos_poster)
        if(movie.posterId != null) RemoteApiService.getRemoteImage(movie.posterId,this.context)!!.into(poster)

        if(movieInFav) {
            Log.e("FAV",movieInFav.toString())
            myView.findViewById<ImageView>(R.id.movie_infos_bookmark2)?.visibility = View.VISIBLE
            myView.findViewById<ImageView>(R.id.movie_infos_bookmark1)?.visibility = View.INVISIBLE
            setBookmarkOff()
        }

        //Ajout du lister de rate
        myView.findViewById<ImageView>(R.id.movie_infos_rate).setOnClickListener{
            listener.onRateClick()
        }

        //Ajout du listner de bookmark
        myView.findViewById<ImageView>(R.id.movie_infos_bookmark1).setOnClickListener{
//            Snackbar.make(myView,"Added to favorites",Snackbar.LENGTH_SHORT).show()
            movie_infos_bookmark2?.visibility = View.VISIBLE
            movie_infos_bookmark1?.visibility = View.INVISIBLE
            listener.onAddBookmark()
        }

        //Remove from fav
        myView.findViewById<ImageView>(R.id.movie_infos_bookmark2).setOnClickListener{
            Snackbar.make(myView,"Removed from favorites",Snackbar.LENGTH_SHORT).show()
            movie_infos_bookmark1.visibility = View.VISIBLE
            movie_infos_bookmark2.visibility = View.INVISIBLE
            listener.onRemoveBookmark()
        }

        return myView
    }

    fun setBookmarkOff(){
//        view?.findViewById<ImageView>(R.id.movie_infos_bookmark2)?.visibility = View.VISIBLE
//        view?.findViewById<ImageView>(R.id.movie_infos_bookmark1)?.visibility = View.INVISIBLE
//        Toast.makeText(activity,"here"+layoutInflater,Toast.LENGTH_LONG).show()
        movie_infos_bookmark2?.visibility = View.VISIBLE
        movie_infos_bookmark1?.visibility = View.INVISIBLE
    }

}