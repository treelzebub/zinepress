package net.treelzebub.zinepress.db.zines;

import android.provider.BaseColumns;

/**
 * Created by Tre Murillo on 1/8/16
 */
public interface ZineCols extends BaseColumns {

    String _TABLE   = "zines_table";

    String ID       = "id";
    String TITLE    = "title";
    String ZINE     = "zine";
    String DATE     = "date";
}
