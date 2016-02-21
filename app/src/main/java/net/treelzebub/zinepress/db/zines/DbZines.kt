package net.treelzebub.zinepress.db.zines

import android.content.Context
import android.database.Cursor
import android.net.Uri
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.db.IDatabase
import net.treelzebub.zinepress.db.IQuery
import net.treelzebub.zinepress.util.BaseInjection

/**
 * Created by Tre Murillo on 1/8/16
 */
object DbZines : IDatabase<IZine> {

    override val context: Context get() = BaseInjection.context

    override fun uri(): Uri {
        return Uri.Builder()
                .scheme("content")
                .authority(context.getString(R.string.authority_zines))
                .build()
    }

    override fun write(): ZineWriter = ZineWriter(this)

    override fun all(): List<IZine> = query().list()

    override fun query(): ZineQuery = ZineQuery(this)

    override fun cursor(query: IQuery<IZine>): Cursor = query.cursor()

    override fun list(query: IQuery<IZine>): List<IZine> = query.list()
}
