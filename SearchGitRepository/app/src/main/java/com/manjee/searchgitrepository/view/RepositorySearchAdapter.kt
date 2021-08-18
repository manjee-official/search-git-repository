package com.manjee.searchgitrepository.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.manjee.searchgitrepository.R
import com.manjee.searchgitrepository.data.model.Repository
import com.manjee.searchgitrepository.databinding.ItemRepositoryBinding

class RepositorySearchAdapter :
    RecyclerView.Adapter<RepositorySearchAdapter.RepositoryViewHolder>() {

    private var repositoryList = arrayListOf<Repository>()

    private var onItemClickListener: ((String) -> Unit)? = null

    inner class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            binding.repository = repositoryList[position]

            binding.layoutContainer.setOnClickListener {
                onItemClickListener?.let {
                    it(repositoryList[position].htmlUrl)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_repository,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    fun setData(repositoryList: ArrayList<Repository>) {
        this.repositoryList.clear()
        this.repositoryList.addAll(repositoryList)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: ((String) -> Unit)) {
        onItemClickListener = listener
    }
}