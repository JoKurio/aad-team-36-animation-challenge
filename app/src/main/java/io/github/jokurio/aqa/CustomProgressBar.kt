package io.github.jokurio.aqa

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.progress_bar.view.*

class CustomProgressBar {
    var dialog: Dialog? = null

    fun show(context: Context): Dialog? {
        return show(context, null)
    }

    fun show(context: Context, title: CharSequence?): Dialog? {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.progress_bar, null)
        if (title != null) {
            view.cp_title.text = title
        }
        view.cp_bg_view.setBackgroundColor(Color.parseColor("#60000000"))
        view.cp_cardview.setCardBackgroundColor(Color.parseColor("#70000000"))
        setColorFilter(view.cp_pbar.indeterminateDrawable,
                ResourcesCompat.getColor(context.resources, R.color.colorPrimary, null))
        view.cp_title.setTextColor(Color.WHITE)

        dialog = Dialog(context, R.style.CustomProgressBarTheme)
        dialog?.setContentView(view)
        dialog?.show()
        return dialog
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
       drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }
}