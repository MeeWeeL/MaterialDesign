package com.meeweel.materialdesign.ui.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.databinding.ToDoRecyclerItemBinding
import kotlinx.android.synthetic.main.to_do_recycler_item.view.*

class ToDoMainFragmentAdapter :
    RecyclerView.Adapter<BaseViewHolder>() {


    private var data: MutableList<Quest> = mutableListOf()
    private var onListItemClickListener: OnListItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = ToDoRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == 1) {
            TitleViewHolder(
                inflater.inflate(R.layout.to_do_recycler_item, parent, false) as View
            )
        } else {
            MainViewHolder(
                inflater.inflate(R.layout.to_do_recycler_item, parent, false) as View
            )
        }
    }
    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> 1
            else -> 0
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            holder.bind(data[position])
        }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MainViewHolder(private val binding: View) :
        BaseViewHolder(binding) {

        override fun bind(quest: Quest) {
            binding.apply {
                toDoRecyclerItemTextView.text = quest.title
                toDoRecyclerItemImageView.setImageResource(quest.image)
                item_description.text = quest.description
                binding.setOnClickListener {
                    onListItemClickListener?.onItemClick(quest)
                }
                delete_btn.setOnClickListener {
                    data.removeAt(layoutPosition)
                    notifyItemRemoved(layoutPosition)
                }
                done_btn.text = "UP"
                done_btn.setOnClickListener {
                    layoutPosition.takeIf { it > 1 }?.also { currentPosition ->
                        data.removeAt(currentPosition).apply {
                            data.add(currentPosition - 1, this)
                        }
                        notifyItemMoved(currentPosition, currentPosition - 1)
                    }

                }
            }
        }
    }

    inner class TitleViewHolder(private val binding: View) :
        BaseViewHolder(binding) {

        override fun bind(quest: Quest) {
            binding.apply {
                toDoRecyclerItemTextView.text = "To-Do List"
                toDoRecyclerItemImageView.setImageResource(R.drawable.anig17)
                item_description.text = "To-Do List"
                item_description.visibility = View.VISIBLE
                delete_btn.visibility = View.GONE
                done_btn.visibility = View.GONE
            }
        }
    }


    fun setQuest(data: MutableList<Quest>) {
        val list = mutableListOf<Quest>(Quest())
        list.addAll(data)
        this.data = list
        notifyDataSetChanged()
    }
    interface OnListItemClickListener {
        fun onItemClick(data: Quest)
    }
    fun setOnItemViewClickListener(a: OnListItemClickListener) {
        onListItemClickListener = a
    }
    fun removeOnItemViewClickListener() {
        onListItemClickListener = null
    }
}