package prj.mob1.prjmob1.Cinema

import prj.mob1.prjmob1.ListItem.BaseFragment
import prj.mob1.prjmob1.ListItem.Item
import prj.mob1.prjmob1.R

/**
 * Created by LE on 20/04/2018.
 */
class CinemaFragment: BaseFragment()
{
    override fun typeAdpter(): Int {
        return 2
    }
    override fun initItem():Array<Item>
    {
        var item= Item(R.drawable.cinema
                ,resources.getString(R.string.cinema_rate).toInt(),
                resources.getString(R.string.cinema_title),
                resources.getString(R.string.cinema_adress).toString())

        var items= arrayOf(item,item,item,item,item,item,item,item,item,item)
        return items
    }
    override fun openActivity(position: Int) {
        //Géolicasation
    }

}
