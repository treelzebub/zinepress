package net.treelzebub.zinepress.util.dialog

import net.treelzebub.zinepress.R

/**
 * Created by Tre Murillo on 1/30/16
 */
class DataLossAlertFragment : BaseAlertDialogFragment(R.layout.dialog_data_loss,
                                                      R.string.alert_data_loss_title,
                                                      R.string.alert_data_loss_message,
                                                      R.string.yes,
                                                      null,
                                                      R.string.no)
