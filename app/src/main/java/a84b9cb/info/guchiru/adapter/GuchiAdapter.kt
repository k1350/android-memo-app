package a84b9cb.info.guchiru.adapter

import a84b9cb.info.guchiru.R
import a84b9cb.info.guchiru.model.Guchi
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.text.SimpleDateFormat

class GuchiAdapter(val context: Context, val comments: MutableList<Guchi>): BaseAdapter() {
    private var mInflater: LayoutInflater? = null

    companion object {
        class CommentViewHolder(view: View) {
            val commentTextView: TextView by lazy { view.findViewById<TextView>(R.id.tv_comment) }
            val dateTextView: TextView by lazy { view.findViewById<TextView>(R.id.tv_date) }
        }
    }

    init {
        mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return comments.size
    }

    override fun getItem(position: Int): Any {
        return comments[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var thisView = view
        val guchi = comments[position]
        val holder: CommentViewHolder

        if (thisView == null) {
            val layoutResourceId = R.layout.list_item
            thisView = mInflater?.inflate(layoutResourceId, null)
            holder = CommentViewHolder(thisView!!)
            thisView.tag = holder
        } else {
            holder = thisView.tag as CommentViewHolder
        }

        holder.commentTextView.text = guchi.comment
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val dateStr =  sdf.format((guchi.date))
        holder.dateTextView.text = dateStr

        return thisView
    }

    fun addList(guchi: Guchi) {
        comments.add(guchi)
    }

    fun removeList(position: Int) {
        comments.removeAt(position)
    }
}