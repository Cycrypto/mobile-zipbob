import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hansotbob.R


data class Review(val foodImg: String, val totalPrice: String, val price: String,
    val title: String, val totalPeople: String, val people: String)

class ReviewAdapter(private val reviewList: MutableList<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImg: ImageView = itemView.findViewById(R.id.foodImg)
        val totalPrice: TextView = itemView.findViewById(R.id.totalPrice)
        val price: TextView = itemView.findViewById(R.id.price)
        val title: TextView = itemView.findViewById(R.id.title)
        val totalPeople: TextView = itemView.findViewById(R.id.totalPeople)
        val people: TextView = itemView.findViewById(R.id.people)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.community_list_box, parent, false)
        return ReviewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviewList[position]
        Glide.with(holder.foodImg.context).load(review.foodImg).into(holder.foodImg)  // 이미지 URL을 ImageView에 로드
        holder.totalPrice.text = review.totalPrice
        holder.price.text = review.price
        holder.title.text = review.title
        holder.totalPeople.text = review.totalPeople
        holder.people.text = review.people

    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun addReview(review: Review) {
        reviewList.add(review)
        notifyDataSetChanged()
    }
}
