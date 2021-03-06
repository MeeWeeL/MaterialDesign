package com.meeweel.materialdesign.ui.picture

import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.ui.MainActivity
import com.meeweel.materialdesign.ui.ThemeHolder
import com.meeweel.materialdesign.ui.chips.ChipsFragment
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import kotlinx.android.synthetic.main.fragment_chips.*
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*

class PictureOfTheDayFragment : Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProviders.of(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData()
            .observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.setTheme(ThemeHolder.theme)
        return inflater.inflate(R.layout.main_fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        input_layout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${input_edit_text.text.toString()}")
            })
        }
        image_view.setOnClickListener {
            ObjectAnimator.ofFloat(image_view, "rotationY", animate_btn.rotationY + 360f).setDuration(1000).start()
        }
        setBottomAppBar(view)
        picture_choose_chips.setOnCheckedChangeListener { chipGroup, position ->
            picture_choose_chips.findViewById<Chip>(position)?.let {
                Toast.makeText(context, "???????????? ${it.text}", Toast.LENGTH_SHORT).show()
                when (it) {
                    todayChip -> {
                            viewModel.getData()
                                .observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
                        }
                    yesterdayChip -> {
                            viewModel.getYesterdayData()
                                .observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
                        }
                    DaysAgoChip -> {
                            viewModel.get2DaysAgoData()
                                .observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
                        }

                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> toast("Favourite")
            R.id.app_bar_settings -> activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, ChipsFragment())?.addToBackStack(null)?.commit()
            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    //showError("??????????????????, ?????? ???????????? ????????????")
                    toast("Link is empty")
                } else {
                    //showSuccess()
                    image_view.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                    }
                }
                val headerText: Spanned = HtmlCompat.fromHtml("<ul><li><b><i>${serverResponseData.title}</i></b></li></ul>", HtmlCompat.FROM_HTML_MODE_LEGACY)
                val descriptionText: SpannableString = SpannableString(serverResponseData.explanation)
                val list = descriptionText.indexesOf("the", false)
                for (i in list) {
                    descriptionText.setSpan(
                        ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.colorAccent)),
                        i,
                        i+3,
                        Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                    )
                }
                val list2 = descriptionText.indexesOf("The", false)
                for (i in list2) {
                    descriptionText.setSpan(
                        ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.colorAccent)),
                        i,
                        i+3,
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                    )
                }

                descriptionText.setSpan(
                    BackgroundColorSpan(ContextCompat.getColor(requireContext(),R.color.colorAccent)),
                    20,
                    32,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                bottom_sheet_description_header.text = headerText
                bottom_sheet_description.text = descriptionText
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            is PictureOfTheDayData.Loading -> {
                //showLoading()
            }
            is PictureOfTheDayData.Error -> {
                //showError(data.error.message)
                toast(data.error.message)
            }
        }
    }
    fun SpannableString?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> {
        val list = mutableListOf<Int>()
        if (this == null || substr.isBlank()) return list

        var i = -1
        while(true) {
            i = indexOf(substr, i + 1, ignoreCase)
            when (i) {
                -1 -> return list
                else -> list.add(i)
            }
        }
    }
    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
        setHasOptionsMenu(true)
        fab.setOnClickListener {
            if (isMain) {
                isMain = false
                bottom_app_bar.navigationIcon = null
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_back_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                isMain = true
                bottom_app_bar.navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_hamburger_menu_bottom_bar)
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_plus_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.CENTER,0,0)
            show()
        }
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
        private var isMain = true
    }
}
