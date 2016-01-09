package net.treelzebub.zinepress.ui.adapter

import android.support.v7.widget.AppCompatCheckBox
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.api.model.PocketArticle
import net.treelzebub.zinepress.util.ToastUtils
import net.treelzebub.zinepress.util.extensions.inflater
import net.treelzebub.zinepress.zine.SelectedArticles

/**
 * Created by Tre Murillo on 1/3/16
 */
class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.ItemHolder> {

    val list: List<PocketArticle>

    constructor(map: Map<String, PocketArticle>) {
        this.list = map.filter { it.value.originalUrl.isNotEmpty() }.values.toList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesAdapter.ItemHolder {
        return ItemHolder(parent.inflater.inflate(R.layout.item_article, parent, false))
    }

    override fun onBindViewHolder(holder: ArticlesAdapter.ItemHolder, position: Int) {
        val article = list.elementAt(position)
        if (article in SelectedArticles.list()) {
            holder.checkbox.isChecked = true
        }
        holder.title.text = safeTitle(article.title)
        holder.url.text   = article.originalUrl
        holder.item.setOnClickListener {
            holder.checkbox.let { it.isChecked = !it.isChecked }
            ToastUtils.show(holder.itemView.context, article.url)
        }
        holder.checkbox.setOnCheckedChangeListener {
            box, isChecked ->
            if (isChecked) {
                SelectedArticles.add(article)
            } else {
                SelectedArticles.remove(article)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return list.elementAt(position).id
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    private fun safeTitle(title: String): String {
        return if (title.isNullOrEmpty()) {
            "[No Title]"
        } else {
            title
        }
    }

    inner class ItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val item:  View                     by bindView(R.id.item)
        val title: TextView                 by bindView(R.id.title)
        val url:   TextView                 by bindView(R.id.url)
        val checkbox: AppCompatCheckBox     by bindView(R.id.checkbox)
    }
}
