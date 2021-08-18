package com.manjee.searchgitrepository.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manjee.searchgitrepository.R
import com.manjee.searchgitrepository.databinding.FragmentRepositorySearchBinding
import com.manjee.searchgitrepository.viewmodel.RepositorySearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RepositorySearchFragment : Fragment() {

    @Inject
    lateinit var repositorySearchAdapter: RepositorySearchAdapter

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
        binding!!.rvSearch.adapter = repositorySearchAdapter
    }

    private fun initObserve() {
        viewModel.getRepositoryLiveData.observe(viewLifecycleOwner) { repositoryList ->
            repositorySearchAdapter.setData(repositoryList)
        }
    }

    private fun initListener() {
        binding!!.btnSearch.setOnClickListener {
            viewModel.getRepositoryList(binding!!.etSearchKeyword.text.toString())
        }

        binding!!.etSearchKeyword.setOnEditorActionListener { _, action, _ ->
            var handled = false
            if (action == EditorInfo.IME_ACTION_DONE) {
                viewModel.getRepositoryList(binding!!.etSearchKeyword.text.toString())
                handled = true
            }
            handled
        }

        repositorySearchAdapter.setOnItemClickListener { url ->
            Toast.makeText(requireContext(), url, Toast.LENGTH_SHORT).show()
        }
    }
}