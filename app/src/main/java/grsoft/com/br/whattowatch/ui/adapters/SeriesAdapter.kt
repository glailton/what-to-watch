package grsoft.com.br.whattowatch.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.databinding.SeriesItemBinding

class SeriesAdapter(
        private val listener: TVShowItemListener
    ): PagedListAdapter<TVShow, SeriesAdapter.ViewHolder>(diffCallback) {

    private lateinit var recyclerView: RecyclerView

    interface TVShowItemListener {
        fun onClicked(tvShow: TVShow)
    }

    private val tvShows: MutableList<TVShow> = mutableListOf()

    fun setItems(tvShows: ArrayList<TVShow>) {
        this.tvShows.clear()
        this.tvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SeriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = getItem(position)

        tvShow?.let {
            holder.apply {
                bindView(tvShow)
                itemView.tag = tvShow
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    class ViewHolder(
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

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TVShow>() {
            override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean = oldItem == newItem
        }
    }
}