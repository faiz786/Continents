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
import android.os.Bundle
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView


class DescriptionFragment : Fragment() {

  companion object {
    private const val ARG_PARAM1 = "name"
    private const val ARG_PARAM2 = "desc"
    private const val ARG_PARAM3 = "image"

    fun newInstance(continent: Continent): DescriptionFragment {
      val fragment = DescriptionFragment()
      val args = Bundle().apply {
        putString(ARG_PARAM1, continent.name)
        putString(ARG_PARAM2, continent.description)
        putInt(ARG_PARAM3, continent.imageResource)
      }
      fragment.arguments = args
      return fragment
    }
  }

  private var continentName = ""
  private var continentDescription = ""
  private var continentImageRes = 0


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      continentName = it.getString(ARG_PARAM1)
      continentDescription = it.getString(ARG_PARAM2)
      continentImageRes = it.getInt(ARG_PARAM3)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_description, container, false)
    val continentImage = view.findViewById<ImageView>(R.id.continentImage)
    continentImage.setImageResource(continentImageRes)

    val description = view.findViewById<TextView>(R.id.continentDescription)
    description.text = continentDescription
    return view
  }
}