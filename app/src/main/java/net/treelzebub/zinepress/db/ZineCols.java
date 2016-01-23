package net.treelzebub.zinepress.db;

import android.provider.BaseColumns;

/**
 * Created by Tre Murillo on 1/8/16
 */
public interface ZineCols extends BaseColumns {
    String _TABLE    = "zines_table";

    String TITLE    = "title";
    String ZINE     = "zine";
    String DATE     = "date";
}
