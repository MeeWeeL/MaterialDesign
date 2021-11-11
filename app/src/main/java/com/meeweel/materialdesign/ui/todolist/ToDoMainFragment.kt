package com.meeweel.materialdesign.ui.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.databinding.ToDoMainFragmentBinding

class ToDoMainFragment : Fragment() {

    companion object {
        fun newInstance() = ToDoMainFragment()
    }
    private val viewModel: ToDoViewModel by lazy {
        ViewModelProvider(this).get(ToDoViewModel::class.java)
    }
    private var _binding: ToDoMainFragmentBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = ToDoMainFragmentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ToDoMainFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        adapter.removeOnItemViewClickListener()
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter.setOnItemViewClickListener(object: ToDoMainFragmentAdapter.OnListItemClickListener {
            override fun onItemClick(data: Quest) {
                activity?.supportFragmentManager?.apply {
                    beginTransaction()
                        .replace(R.id.container, ToDoDetailsFragment.newInstance(Bundle().apply {
                            putParcelable(ToDoDetailsFragment.BUNDLE_EXTRA, data)
                        }))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }
        })
        binding.mainFragmentRecyclerView.adapter = adapter
        val observer = Observer<ToDoAppState> { a ->
            renderData(a)
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getQuestFromLocalSource()
    }


    private fun renderData(data: ToDoAppState) = when (data) {
        is ToDoAppState.Success -> {
            val questData = data.questData
            binding.loadingLayout.visibility = View.GONE
            adapter.setQuest(questData)
        }
        is ToDoAppState.Loading -> {
            binding.loadingLayout.visibility = View.VISIBLE
        }
        is ToDoAppState.Error -> {
            binding.loadingLayout.visibility = View.GONE

        }
    }

}