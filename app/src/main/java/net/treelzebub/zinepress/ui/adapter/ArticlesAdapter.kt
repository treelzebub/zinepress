package net.treelzebub.zinepress.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.api.model.PocketArticle
import net.treelzebub.zinepress.util.ToastUtils

/**
 * Created by Tre Murillo on 1/3/16
 */
class ArticlesAdapter(val map: Map<String, PocketArticle>) : RecyclerView.Adapter<ArticlesAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesAdapter.ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false))
    }

    override fun onBindViewHolder(holder: ArticlesAdapter.ItemHolder, position: Int) {
        val article = map.values.elementAt(position)
        holder.title.text = article.title
        holder.url.text   = article.originalUrl
        holder.itemView.setOnClickListener {
            ToastUtils.show(holder.itemView.context, article.url)
        }
    }

    override fun getItemCount(): Int {
        return map.size
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    inner class ItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView by bindView(R.id.title)
        val url:   TextView by bindView(R.id.url)
    }
}
