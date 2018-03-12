package com.pridevel.continents

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.security.AccessControlContext

/**
 * Created by Administrator on 12/30/2017.
 */



public class Adapter(context: Context,continent: ArrayList<Continent>) : BaseAdapter()
{
    var continentSource: ArrayList<Continent> = continent

    val mInflater : LayoutInflater = LayoutInflater.from(context)

//    val mInflater : LayoutInflater
//    init {
//        this.mInflater = LayoutInflater.from(context);
//    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? {
        //init
        val rowView = mInflater.inflate(R.layout.list_layout, parent, false)
        val titleTextView = rowView.findViewById(R.id.continent_name) as TextView

        titleTextView.setText(continentSource.get(position).name);

        return rowView
    }

    override fun getItem(p0: Int): Any {
        return  continentSource.get(p0);
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong();
    }

    override fun getCount(): Int {
       return continentSource.size
    }

}