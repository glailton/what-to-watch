package grsoft.com.br.whattowatch.ui.series.details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import grsoft.com.br.whattowatch.data.entities.CastList
import grsoft.com.br.whattowatch.databinding.CastItemBinding
import grsoft.com.br.whattowatch.ui.extensions.loadCircleImage
import grsoft.com.br.whattowatch.utils.BASE_URL

class CastAdapter : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    private var inflater: LayoutInflater? = null
    private val cast: MutableList<CastList> = mutableListOf()

    var onItemClick: ((position: Int) -> Unit)? = null

    fun setItems(cast: List<CastList>) {
        this.cast.clear()
        this.cast.addAll(cast)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }

        val binding = CastItemBinding.inflate(inflater!!, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastAdapter.ViewHolder, position: Int) {
        val cast = cast[position]

        holder.bind(cast, position)
    }

    override fun getItemCount() = cast.size

    fun getItem(position: Int): CastList? {
        return if (cast.isNullOrEmpty() || position < 0 || position > cast.size) {
            null
        } else cast[position]
    }

    inner class ViewHolder(val binding: CastItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(cast: CastList, position: Int) {
            cast.let {
                binding.castImage.loadCircleImage(BASE_URL + cast.profilePath)
                binding.castName.text = cast.name
                binding.castCharacter.text = cast.character
            }

            binding.root.setOnClickListener {
                onItemClick?.invoke(position)
            }
        }
    }

}