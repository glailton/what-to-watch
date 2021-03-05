package grsoft.com.br.whattowatch.ui.extensions

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import android.text.SpannableStringBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import grsoft.com.br.whattowatch.data.entities.ProductionCompany
import grsoft.com.br.whattowatch.utils.BASE_URL


fun ProgressBar.hide() {
    visibility = View.GONE
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun TextView.hide() {
    visibility = View.GONE
}

fun TextView.show() {
    visibility = View.VISIBLE
}

fun TextView.setTextList(list: List<ProductionCompany>) {
    val ssb = SpannableStringBuilder()
    list.forEach {
        ssb.append(it.prodCompanyName)
        ssb.append("\n")
    }
    this.text = ssb.toString()
}

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
}