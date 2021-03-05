package grsoft.com.br.whattowatch.ui.series.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.databinding.SeriesItemBinding
import grsoft.com.br.whattowatch.ui.extensions.loadImage
import grsoft.com.br.whattowatch.utils.BASE_URL

class SeriesAdapter: PagedListAdapter<TVShow, SeriesAdapter.ViewHolder>(diffCallback) {

    private lateinit var recyclerView: RecyclerView

    var onItemClick: ((entity: TVShow) -> Unit)? = null

    private val tvShows: MutableList<TVShow> = mutableListOf()

    fun setItems(tvShows: ArrayList<TVShow>) {
        this.tvShows.clear()
        this.tvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SeriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
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

    inner class ViewHolder(
        private val binding: SeriesItemBinding): RecyclerView.ViewHolder(binding.root) {

        private lateinit var tvShow: TVShow
        private val imageViewPoster = binding.imagePoster
        private var title = binding.tvTitle

        fun bindView(tvShow: TVShow) {
            this.tvShow = tvShow
            imageViewPoster.loadImage(BASE_URL + tvShow.posterPath)
            title.text = tvShow.name

            binding.root.setOnClickListener {
                onItemClick?.invoke(tvShow)
            }
        }

    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TVShow>() {
            override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean = oldItem == newItem
        }
    }
}