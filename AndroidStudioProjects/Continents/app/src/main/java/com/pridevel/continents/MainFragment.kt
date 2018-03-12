/*
 * Copyright (c) 2017 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.pridevel.continents

import android.support.v4.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
//import com.pridevel.continents.util.ContextExtensions.toast


class MainFragment : Fragment() {

  private lateinit var listView: ListView
  private lateinit var listAdapter: ArrayAdapter<String>
  private lateinit var listAdapter1: Adapter
  private lateinit var continents: Array<String>
  private lateinit var continents1: ArrayList<Continent>
  private var listener: ContinentSelectedListener? = null
  private var listener1: ContinentSelectedListenerParcel? = null

  companion object {
    private const val ARG_PARAM1 = "continentNames"

//    fun newInstance(continents: Array<String>): MainFragment {
//      val fragment = MainFragment()
//      val args = Bundle().apply {
//        putStringArray(ARG_PARAM1, continents)
//      }
//      fragment.arguments = args
//      return fragment
//    }

    fun newInstance1(continents1: ArrayList<Continent>): MainFragment {
      val fragment = MainFragment()
      val args = Bundle().apply {
        putParcelableArrayList(ARG_PARAM1, continents1)
      }
      fragment.arguments = args
      return fragment
    }

  }



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
//      continents = it.getStringArray(ARG_PARAM1)
      continents1 = it.getParcelableArrayList(ARG_PARAM1)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_main, container, false)

    listView = view.findViewById(R.id.mainListView)

    // Create ArrayAdapter using the continent list.
//    listAdapter = ArrayAdapter(this.activity, android.R.layout.simple_list_item_1, continents)
    listAdapter1 = Adapter(this.activity,continents1)
    listView.adapter = listAdapter1
//    listView.setOnItemClickListener { _, _, position, _ ->
//      if (listener == null) {
//        listener = activity as ContinentSelectedListener
//      }
//      listener?.onContinentSelected(continents[position])
//    }
    listView.setOnItemClickListener { _, _, position, _ ->
      if (listener1 == null) {
        listener1 = activity as ContinentSelectedListenerParcel
      }
      listener1?.onContinentSelected(continents1[position])
    }
//    listView.setOnItemClickListener { parent, view, position, id ->
//
////      val position1 = position;
//      fun Context.toast(message: String) =
//              Toast.makeText(this, message+position, Toast.LENGTH_SHORT).show()
//
//      fun myFun(context: Context) {
//        context.toast("Hello world!")
//      }
////      Toast.makeText(MainFragment.this , "Position Clicked:"+" "+position,Toast.LENGTH_SHORT).show();
//    }
    return view
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    try {
//      listener = context as ContinentSelectedListener
      listener1 = context as ContinentSelectedListenerParcel
    } catch (e: ClassCastException) {
      throw ClassCastException("$context must implement ContinentSelectedListener")
    }
  }

  fun updateContinents(continentList: ArrayList<Continent>) {
    continents1 = continentList
//    listAdapter = android.widget.ArrayAdapter(this.activity, android.R.layout
//        .simple_list_item_1, continents)

    listAdapter1 = Adapter(this.activity,continents1)
    listView.adapter = listAdapter1
    listAdapter1.notifyDataSetChanged()
  }

  override fun onDetach() {
    super.onDetach()
//    listener = null
    listener1 = null
  }
}