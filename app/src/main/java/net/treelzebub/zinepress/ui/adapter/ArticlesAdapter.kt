package net.treelzebub.zinepress.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import net.treelzebub.zinepress.api.model.PocketArticle

/**
 * Created by Tre Murillo on 1/3/16
 */
class ArticlesAdapter(val map: Map<String, PocketArticle>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        throw UnsupportedOperationException()
    }

    override fun getItemCount(): Int {
        throw UnsupportedOperationException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = map.values.elementAt(position)

    }
}
