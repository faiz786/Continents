package com.pridevel.continents



import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity(), ContinentSelectedListener,ContinentSelectedListenerParcel {


    private lateinit var title: TextView
    private lateinit var orderContinents: Spinner
    private var mainFragment: MainFragment? = null
    private var continentMap: MutableMap<String, Continent> = HashMap()
    private var continentMap1: MutableList<Continent> = ArrayList()
//    private var continentMap1: Array<Continent> = arrayOf();

    val continentNames = arrayOf("Asia", "Africa", "Australia", "Antarctica", "Europe",
            "North America", "South America")

    private val continentDescs: Array<String> = arrayOf(
            """
            |Asia is the Earth's largest and most populous continent. It covers 8.7% of
            |Earth's total surface area. As of 2013, it had a population of 4.3 billion.
            |It consists of 47 countries.""".trimMargin(),
            """
            |Africa is the Earth's second largest and second most populous continent.
            |It covers 6% of Earth's total surface area.
            |As of 2013, it had a population of 1.1 billion. It consists of 54 countries.""".trimMargin(),
            """
            |Australia is both a country and a continent.
            |As of 2013, it had a population of 23 million.""".trimMargin(),
            """
            |Antarctica is the Earth's southernmost continent. It is the fifth largest continent.
            |About 98% of Antarctica is covered by ice.""".trimMargin(),
            """
            |Europe is the Earth's second smallest continent. It covers 2% of Earth's surface area.
            |As of 2013, it had a population of 742.5 million. It consists of 50 countries.""".trimMargin(),
            """
            |North America is the Earth's third largest continent and fourth most populous continent.
            |It covers about 16.5% of the Earth's surface area.
            |As of 2013, it had a population of about 565 million.
            |It consists of 23 countries.""".trimMargin(),
            """
            |South America is the Earth's fourth largest continent and fifth most populous continent.
            |As of 2011, it had a population of about 385.7 million. It consists of 12 countries""".trimMargin()
    )

    private val imageResources = arrayOf(R.drawable.asia, R.drawable.africa, R.drawable
            .australia, R.drawable.antarctica, R.drawable.europe, R.drawable.north_america, R.drawable
            .south_america)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        for ((i, name) in continentNames.withIndex()) {
//            val continent = Continent(name, continentDescs[i], imageResources[i])
//            continentMap.put(name, continent)
//        }


        for ((i, name) in continentNames.withIndex()) {
            val continent = Continent(name, continentDescs[i], imageResources[i])
            continentMap1.add(i,continent);
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        title = toolbar.findViewById(R.id.title)
        orderContinents = toolbar.findViewById(R.id.toolbar_spinner)
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayOf("A - Z", "Z - A"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        orderContinents.adapter = adapter
        orderContinents.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View,
                                        position: Int, id: Long) {
                when (position) {
                    0 -> {
                        continentNames.sort()
                        mainFragment?.updateContinents(continentMap1 as ArrayList<Continent>)
                    }
                    1 -> {
                        continentNames.reverse()
                        mainFragment?.updateContinents(continentMap1 as ArrayList<Continent>)
                    }
                    else -> {
                    }
                }

            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>) {}
        }

        setSupportActionBar(toolbar)
        goToContinentList()
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        goToContinentList()
        return true
    }

    override fun onContinentSelected(selectedContinent: String) {
        val continent = continentMap[selectedContinent]
//        val continent1 = continentMap1[]
        continent?.let {
            title.text = continent.name
            orderContinents.visibility = View.INVISIBLE
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            val descriptionFragment = DescriptionFragment.newInstance(continent)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                descriptionFragment.enterTransition = Fade()
                mainFragment?.exitTransition = Fade()
                descriptionFragment.exitTransition = Slide(Gravity.BOTTOM)
                mainFragment?.reenterTransition = Fade()
                descriptionFragment.allowReturnTransitionOverlap = true
            }

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout, descriptionFragment)
                    .addToBackStack(null)
                    .commit()
        }
    }

    override fun onContinentSelected(selectedContinent: Continent) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        selectedContinent?.let {
            title.text = selectedContinent.name
            orderContinents.visibility = View.INVISIBLE
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            val descriptionFragment = DescriptionFragment.newInstance(selectedContinent)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                descriptionFragment.enterTransition = Fade()
                mainFragment?.exitTransition = Fade()
                descriptionFragment.exitTransition = Slide(Gravity.BOTTOM)
                mainFragment?.reenterTransition = Fade()
                descriptionFragment.allowReturnTransitionOverlap = true
            }

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout, descriptionFragment)
                    .addToBackStack(null)
                    .commit()
        }
    }

    private fun goToContinentList() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        title.text = getString(R.string.continents)
        orderContinents.visibility = View.VISIBLE
        mainFragment = MainFragment.newInstance1(continentMap1 as ArrayList<Continent>)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout, mainFragment)
                .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        title.text = getString(R.string.continents)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
