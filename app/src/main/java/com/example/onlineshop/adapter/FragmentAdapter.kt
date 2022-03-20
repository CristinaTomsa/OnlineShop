package com.example.onlineshop.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.onlineshop.data.Subcategory
import com.example.onlineshop.fragments.ProductFragment

class FragmentAdapter (fm: FragmentManager): FragmentPagerAdapter(fm){

    var fragmentList: ArrayList<Fragment> = ArrayList()
    var titleList: ArrayList<String> = ArrayList()
    override fun getCount(): Int {
        return titleList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

//    fun addFragment(newList: List<Subcategory>){
//        for(subCat in newList){
//            fragmentList.add(ProductFragment.newInstance(subCat.subcategory_id))
//            titleList.add(subCat.subcategory_name)
//        }
//    }

}