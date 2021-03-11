package grsoft.com.br.whattowatch.ui.series.details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import grsoft.com.br.whattowatch.data.entities.VideoList
import grsoft.com.br.whattowatch.databinding.VideosItemBinding
import grsoft.com.br.whattowatch.ui.extensions.loadImage
import grsoft.com.br.whattowatch.utils.YOUTUBE_THUMBNAIL

class VideosAdapter : RecyclerView.Adapter<VideosAdapter.ViewHolder>() {

    private var inflater: LayoutInflater? = null
    private val videos: MutableList<VideoList> = mutableListOf()

    var onItemClick: ((position: Int) -> Unit)? = null

    fun setItems(videos: List<VideoList>) {
        this.videos.clear()
        this.videos.addAll(videos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }

        val binding = VideosItemBinding.inflate(inflater!!, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideosAdapter.ViewHolder, position: Int) {
        val videos = videos[position]

        holder.bind(videos, position)
    }

    override fun getItemCount() = videos.size

    fun getItem(position: Int): VideoList? {
        return if (videos.isNullOrEmpty() || position < 0 || position > videos.size) {
            null
        } else videos[position]
    }

    inner class ViewHolder(val binding: VideosItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(videos: VideoList, position: Int) {
            videos.let {
                binding.videoThumbnail.loadImage(String.format(YOUTUBE_THUMBNAIL, it.key))
                binding.videoName.text = it.name
            }

            binding.root.setOnClickListener {
                onItemClick?.invoke(position)
            }
        }
    }

}