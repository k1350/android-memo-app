package a84b9cb.info.guchiru


import a84b9cb.info.guchiru.adapter.GuchiAdapter
import a84b9cb.info.guchiru.model.Guchi
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.*
import android.widget.*

import java.util.*


class MainListFragment : Fragment() {

    var mCommentListView: MyListView? = null
    var comments = mutableListOf<Guchi>()
    var adapter: GuchiAdapter? = null
    var mEtGuchi: EditText? = null

    override fun onCreateView(inflater: LayoutInflater,
                              @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mCommentListView = view?.findViewById<View>(R.id.lv_commentView) as MyListView
        mEtGuchi = view?.findViewById<EditText>(R.id.et_guchi)
        adapter = GuchiAdapter(activity, comments)
        mCommentListView?.adapter = adapter

        val btnSubmit: ImageButton = view?.findViewById<ImageButton>(R.id.bt_submit)
        btnSubmit.setOnClickListener { view ->
            val str = mEtGuchi?.text.toString()
            if (str != null && !str.isEmpty()) {
                adapter?.addList(Guchi(str, Date()))
                adapter?.notifyDataSetChanged()
                mEtGuchi?.text?.clear()
                mCommentListView?.setSelection(adapter!!.count - 1)
            }
        }

        registerForContextMenu(mCommentListView)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, view: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, view, menuInfo)
        val inflater: MenuInflater = activity.menuInflater
        inflater.inflate(R.menu.menu_context_menu_list, menu)
        menu?.setHeaderIcon(android.R.drawable.ic_menu_edit)
        menu?.setHeaderIcon(android.R.drawable.ic_menu_delete)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val info = item?.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        val itemId = item?.itemId

        when(itemId) {
            R.id.menuListOptionDelete -> {
                adapter?.removeList(listPosition)
                adapter?.notifyDataSetChanged()
            }
        }

        return super.onContextItemSelected(item)
    }


}// Required empty public constructor
