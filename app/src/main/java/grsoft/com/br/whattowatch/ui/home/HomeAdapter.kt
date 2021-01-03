package grsoft.com.br.whattowatch.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import grsoft.com.br.whattowatch.data.model.Series
import grsoft.com.br.whattowatch.databinding.SeriesItemBinding
import grsoft.com.br.whattowatch.ui.home.HomeAdapter.HomeViewHolder

class HomeAdapter(
    private val seriesList: List<Series>,
    private val onItemClickListener: ((series: Series) -> Unit)): RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = SeriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val series = seriesList[position]

        holder.bindView(series, onItemClickListener)
    }

    override fun getItemCount() = seriesList.size

    class HomeViewHolder(private val binding: SeriesItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val imageViewPoster = binding.imagePoster

        fun bindView(series: Series, listener: (series: Series) -> Unit) {
            Picasso.get()
                .load(series.thumbnailPath)
                .into(imageViewPoster)
            binding.root.setOnClickListener {
                listener(series)
            }
        }

    }
}