package a84b9cb.info.guchiru


import a84b9cb.info.guchiru.adapter.GuchiAdapter
import a84b9cb.info.guchiru.model.Guchi
import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        mCommentListView = view?.findViewById<View>(R.id.lvCommentView) as MyListView
        mEtGuchi = view?.findViewById<EditText>(R.id.et_guchi)
        comments.add(Guchi("てすともじれつだよー", Date()))
        adapter = GuchiAdapter(activity, comments)
        mCommentListView?.adapter = adapter

        val btnSubmit: ImageButton = view?.findViewById<ImageButton>(R.id.bt_submit)
        btnSubmit.setOnClickListener { view ->
            val str = mEtGuchi?.text.toString()
            if (str != null && !str.isEmpty()) {
                adapter?.addList(Guchi(str, Date()))
                adapter?.notifyDataSetChanged()
                mEtGuchi?.text?.clear()
            }
        }
    }


}// Required empty public constructor
