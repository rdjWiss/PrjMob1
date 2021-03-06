package prj.mob1.prjmob1.Person

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import prj.mob1.prjmob1.retrofitUtil.models.PersonMovieCastCredits
import prj.mob1.prjmob1.retrofitUtil.models.PersonMovieCredits
import prj.mob1.prjmob1.retrofitUtil.models.PersonTVCredits

/**
 * Created by sol on 16/04/2018.
 */
/*

"adult": false,
  "also_known_as": [],
  "biography": "William Bradley \"Brad\" Pitt (born December 18, 1963) is an American actor and film producer. Pitt has received two Academy Award nominations and four Golden Globe Award nominations, winning one. He has been described as one of the world's most attractive men, a label for which he has received substantial media attention. Pitt began his acting career with television guest appearances, including a role on the CBS prime-time soap opera Dallas in 1987. He later gained recognition as the cowboy hitchhiker who seduces Geena Davis's character in the 1991 road movie Thelma &amp; Louise. Pitt's first leading roles in big-budget productions came with A River Runs Through It (1992) and Interview with the Vampire (1994). He was cast opposite Anthony Hopkins in the 1994 drama Legends of the Fall, which earned him his first Golden Globe nomination. In 1995 he gave critically acclaimed performances in the crime thriller Seven and the science fiction film 12 Monkeys, the latter securing him a Golden Globe Award for Best Supporting Actor and an Academy Award nomination.\n\nFour years later, in 1999, Pitt starred in the cult hit Fight Club. He then starred in the major international hit as Rusty Ryan in Ocean's Eleven (2001) and its sequels, Ocean's Twelve (2004) and Ocean's Thirteen (2007). His greatest commercial successes have been Troy (2004) and Mr. &amp; Mrs. Smith (2005).\n\nPitt received his second Academy Award nomination for his title role performance in the 2008 film The Curious Case of Benjamin Button. Following a high-profile relationship with actress Gwyneth Paltrow, Pitt was married to actress Jennifer Aniston for five years. Pitt lives with actress Angelina Jolie in a relationship that has generated wide publicity. He and Jolie have six children—Maddox, Pax, Zahara, Shiloh, Knox, and Vivienne.\n\nSince beginning his relationship with Jolie, he has become increasingly involved in social issues both in the United States and internationally. Pitt owns a production company named Plan B Entertainment, whose productions include the 2007 Academy Award winning Best Picture, The Departed.\n\nDescription above from the Wikipedia article Brad Pitt, licensed under CC-BY-SA, full list of contributors on Wikipedia.",
  "birthday": "1963-12-18",
  "deathday": "",
  "gender": 0,
  "homepage": "",
  "id": 287,
  "imdb_id": "nm0000093",
  "name": "Brad Pitt",
  "place_of_birth": "Shawnee - Oklahoma - USA",
  "popularity": 1.35777,
  "profile_path": "/lZngQUfDpPwlBRebtFo8XFuk9T3.jpg"
 */
data class Person ( @SerializedName("id") val id:Int,
                    @SerializedName("name")val nom:String,
                    @SerializedName("birthday") var birthday:String,
                    @SerializedName("place_of_birth") var origin : String,
                    @SerializedName("biography") var biography:String,
                    @SerializedName("profile_path") val imageId: String?,
                    val posterId:Int,
                    @SerializedName("movie_credits") val movieCredits: PersonMovieCredits,
                    @SerializedName("tv_credits") val tvCredits: PersonTVCredits,
                    var imageTop:String?) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            PersonMovieCredits(listOf()),
            PersonTVCredits(listOf()),
            parcel.readString()
            ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nom)
        parcel.writeString(birthday)
        parcel.writeString(origin)
        parcel.writeString(biography)
        parcel.writeString(imageId)
        parcel.writeInt(posterId)
        parcel.writeString(imageTop)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }

    constructor() : this(0,"NA","NA","NA","NA","NA",0,PersonMovieCredits(listOf()),PersonTVCredits(listOf()),"")
}