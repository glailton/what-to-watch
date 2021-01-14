package grsoft.com.br.whattowatch.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.databinding.SeriesItemBinding
import grsoft.com.br.whattowatch.ui.home.HomeAdapter.HomeViewHolder

class HomeAdapter(
    private val listener: TVShowItemListener): RecyclerView.Adapter<HomeViewHolder>() {

    interface TVShowItemListener {
        fun onClicked(tvShow: TVShow)
    }

    private val tvShows: MutableList<TVShow> = mutableListOf()

    fun setItems(tvShows: ArrayList<TVShow>) {
        this.tvShows.clear()
        this.tvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = SeriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val tvShow = tvShows[position]

        holder.bindView(tvShow)
    }

    override fun getItemCount() = tvShows.size

    class HomeViewHolder(
        binding: SeriesItemBinding,
        private val listener: TVShowItemListener): RecyclerView.ViewHolder(binding.root),
        View.OnClickListener{

        private lateinit var tvShow: TVShow
        private val imageViewPoster = binding.imagePoster
        private var title = binding.tvTitle
        private val BASE_URL: String = "https://image.tmdb.org/t/p/w185"

        fun bindView(tvShow: TVShow) {
            this.tvShow = tvShow
            Picasso.get()
                .load(BASE_URL + tvShow.posterPath)
                .into(imageViewPoster)
            title.text = tvShow.name
        }

        override fun onClick(view: View?) {
            listener.onClicked(tvShow)
        }

    }
}