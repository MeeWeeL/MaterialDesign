package com.meeweel.materialdesign.ui.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.databinding.ToDoEditLayoutBinding
import com.meeweel.materialdesign.ui.todolist.todorepository.images

class ToDoCreateFragment : Fragment() {

    private var imageInt: Int = 0
    private var _binding: ToDoEditLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ToDoViewModel by lazy {
        ViewModelProvider(this).get(ToDoViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ToDoEditLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Quest>(BUNDLE_EXTRA)?.let {
                quest -> populateData(quest)
        }
    }

    private fun populateData(questData: Quest) {
        with(binding) {
            image.setImageResource(questData.image)
            title.setText(questData.title)
            descriptionValue.setText(questData.description)
            imageInt = questData.imageInt
            nextBtn.setOnClickListener {
                imageInt++
                if (imageInt == images.size) imageInt = 0
                image.setImageResource(images[imageInt])
            }
            backBtn.setOnClickListener {
                imageInt--
                if (imageInt < 0) imageInt = images.size - 1
                image.setImageResource(images[imageInt])
            }
            saveBtn.setOnClickListener {
                val item: Quest = Quest(1,"","", images[0],0)
                item.description = descriptionValue.text.toString()
                item.title = title.text.toString()
                item.image = images[imageInt]
                item.imageInt = imageInt
                viewModel.saveNewQuest(item)
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, ToDoMainFragment())?.commitNow()
            }
            cancelBtn.setOnClickListener {
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, ToDoMainFragment())?.commitNow()
            }
        }
    }
    companion object {
        const val BUNDLE_EXTRA = "quest"

        fun newInstance(bundle: Bundle): ToDoCreateFragment {
            val fragment = ToDoCreateFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}