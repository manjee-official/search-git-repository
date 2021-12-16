package com.manjee.searchgitrepository.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manjee.searchgitrepository.databinding.FragmentRepositorySearchBinding
import com.manjee.searchgitrepository.viewmodel.RepositorySearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RepositorySearchFragment : Fragment() {

    @Inject
    lateinit var repositorySearchAdapter: RepositorySearchAdapter

    private val viewModel by viewModels<RepositorySearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentRepositorySearchBinding.inflate(inflater, container, false).run {
            lifecycleOwner = this@RepositorySearchFragment
            vm = viewModel

            initRvSearch()
            observedVm()
            setEventListener()

            root
        }
    }

    private fun FragmentRepositorySearchBinding.initRvSearch() {
        rvSearch.adapter = repositorySearchAdapter

        repositorySearchAdapter.setOnItemClickListener { url ->
            Toast.makeText(requireContext(), url, Toast.LENGTH_SHORT).show()
        }
    }

    private fun FragmentRepositorySearchBinding.observedVm() {
        viewModel.apply {
            getRepositoryLiveData.observe(viewLifecycleOwner) { repositoryList ->
                repositorySearchAdapter.setData(repositoryList)
            }
        }
    }

    private fun FragmentRepositorySearchBinding.setEventListener() {
        etSearchKeyword.setOnEditorActionListener { _, action, _ ->
            var handled = false
            if (action == EditorInfo.IME_ACTION_DONE) {
                viewModel.getRepositoryList(etSearchKeyword.text.toString())
                handled = true
            }
            handled
        }
    }
}