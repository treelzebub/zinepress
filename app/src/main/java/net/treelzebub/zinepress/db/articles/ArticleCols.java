package net.treelzebub.zinepress.db.articles;

import android.provider.BaseColumns;

/**
 * Created by Tre Murillo on 2/19/16
 */
public interface ArticleCols extends BaseColumns {

    String _TABLE = "articles_table";

    String ID = "id";
    String DATE = "date";
    String TITLE = "title";
    String URL = "url";
}
