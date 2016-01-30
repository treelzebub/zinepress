package net.treelzebub.zinepress.util.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Tre Murillo on 1/30/16
 */
abstract class BaseDialogFragment(@LayoutRes val layout: Int,
                                       @StringRes val title: Int,
                                       @StringRes val message: Int,
                                       @StringRes val positive: Int?,
                                       @StringRes val neutral: Int?,
                                       @StringRes val negative: Int?) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog? {
        val builder = AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
        if (positive != null) {
            builder.setPositiveButton(positive, { dialog, which -> onPositive(null) })
        }
        if (neutral != null) {
            builder.setNeutralButton(neutral, { dialog, which -> onNeutral(null) })
        }
        if (negative != null) {
            builder.setNegativeButton(negative, { dialog, which -> onNegative(null) })
        }
        return builder.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layout, container, false).apply {
            onCreateDialogView(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Override
    }

    protected open fun onCreateDialogView(v: View?) {
        // Override
    }

    protected open fun onPositive(v: View?) {
        onButton(v, Activity.RESULT_OK)
    }

    protected open fun onNeutral(v: View?) {
        onButton(v, 0)
    }

    protected open fun onNegative(v: View?) {
        onButton(v, Activity.RESULT_CANCELED)
    }

    protected open fun onButton(v: View?, code: Int) {
        targetFragment?.onActivityResult(targetRequestCode, code, buildReturnIntent(code))
    }

    private fun buildReturnIntent(code: Int): Intent {
        val retval = Intent()
        if (code == Activity.RESULT_OK) {
            val args = Bundle()
            //            onReturnBundle(args)
            retval.putExtras(args)
        }
        return retval
    }
}
