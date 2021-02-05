package grsoft.com.br.whattowatch.ui.series.details.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPageAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragmentList: MutableList<Fragment> = arrayListOf()
    private val mFragmentTitleList: MutableList<String> = arrayListOf()

    override fun getCount() = mFragmentList.size


    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment?, title: String?) {
        fragment?.let { mFragmentList.add(it) }
        title?.let { mFragmentTitleList.add(it) }
    }

}