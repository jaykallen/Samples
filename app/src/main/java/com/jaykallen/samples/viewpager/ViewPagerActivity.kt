package com.jaykallen.samples.viewpager

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_viewpager.*
import timber.log.Timber
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jaykallen.samples.R
import kotlinx.android.synthetic.main.viewpage_stock.view.*


/**
 * Created by jaykallen on 4/21/18.  First work on being able to edit a single portfolio, then add multiple
 * portfolios.
 */

class ViewPagerActivity : AppCompatActivity() {
    var stocks : ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)
        Timber.d(" *** ViewPager Activity *** ")
        stocks.add("Hello")
        stocks.add("To")
        stocks.add("You")
        stocks.add("Out")
        stocks.add("There")
        viewPager.adapter = PageAdapter(this, stocks)
    }

    inner class PageAdapter(private val context: Context, private var itemList: ArrayList<String>): PagerAdapter() {

        override fun getCount(): Int {
            return itemList.size
        }
        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }
        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View)
        }
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val page = inflater.inflate(R.layout.viewpage_stock, container, false)
            page.textView.text = itemList[position]
            container.addView(page)
            return page
        }
    }
}