package com.meeweel.materialdesign.ui.other.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.ui.ThemeHolder
import com.meeweel.materialdesign.ui.other.OtherModel
import com.meeweel.materialdesign.ui.picture.PictureOfTheDayData
import com.meeweel.materialdesign.ui.picture.PictureOfTheDayFragment
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.moon_layout.*

class PlanetFragment : Fragment() {

    private val viewModel: OtherModel by lazy {
        ViewModelProviders.of(this).get(OtherModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getPlanet()
            .observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.moon_layout, container, false)
    }


    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    //showError("Сообщение, что ссылка пустая")
                    toast("Link is empty")
                } else {
                    //showSuccess()
                    moon_image.load(url) {
                        lifecycle(this@PlanetFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                    }
                }
                moon_text.text = serverResponseData.explanation
                moon_title.text = serverResponseData.title
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

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.CENTER,0,0)
            show()
        }
    }

    companion object {
        fun newInstance() = PlanetFragment()
        private var isMain = true
    }
}