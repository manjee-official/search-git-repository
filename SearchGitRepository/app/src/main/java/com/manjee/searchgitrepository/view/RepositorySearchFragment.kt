package com.manjee.searchgitrepository.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manjee.searchgitrepository.R
import com.manjee.searchgitrepository.databinding.FragmentRepositorySearchBinding
import com.manjee.searchgitrepository.viewmodel.RepositorySearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositorySearchFragment : Fragment() {

    private var binding: FragmentRepositorySearchBinding? = null
    private val viewModel by viewModels<RepositorySearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentRepositorySearchBinding?>(
            inflater,
            R.layout.fragment_repository_search,
            container,
            false
        ).apply {
            lifecycleOwner = this@RepositorySearchFragment
        }
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserve()
        initListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initView() {
        viewModel.getRepositoryList()
    }

    private fun initObserve() {

    }

    private fun initListener() {

    }

}