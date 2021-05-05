package grsoft.com.br.whattowatch.ui.Feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import grsoft.com.br.whattowatch.data.models.FeedItem
import grsoft.com.br.whattowatch.databinding.ItemFeedBinding
import grsoft.com.br.whattowatch.ui.series.adapters.SeriesAdapter

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    private var inflater: LayoutInflater? = null

    private val feedItems: MutableList<FeedItem> = mutableListOf()

    fun setItems(feedItems: ArrayList<FeedItem>) {
        this.feedItems.clear()
        this.feedItems.addAll(feedItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }

        val binding = ItemFeedBinding.inflate(inflater!!, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedAdapter.ViewHolder, position: Int) {
        val feedItem = feedItems[position]

        holder.bind(feedItem)
    }

    override fun getItemCount() = feedItems.size

    inner class ViewHolder(val binding: ItemFeedBinding): RecyclerView.ViewHolder(binding.root) {

        private val seriesAdapter by lazy {
            val adapter = SeriesAdapter().apply {
                setHasStableIds(true)
            }
            binding.recyclerSeries.adapter = adapter
            adapter
        }

        fun bind(feed: FeedItem) {
            if (!feed.tvShows.isNullOrEmpty()) {
                binding.textViewGenre.text = feed.genre
                seriesAdapter.setItems(ArrayList(feed.tvShows))
            }
        }
    }

}