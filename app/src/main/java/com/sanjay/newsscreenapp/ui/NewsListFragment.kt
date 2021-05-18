package com.sanjay.newsscreenapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanjay.newsscreenapp.R
import com.sanjay.newsscreenapp.adapter.NewsAdapter
import com.sanjay.newsscreenapp.databinding.FragmentNewsListBinding
import com.sanjay.newsscreenapp.utils.NetworkStatus


class NewsListFragment : Fragment() {

    private var _binding:FragmentNewsListBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsAdapter: NewsAdapter

    private val newsViewModel:NewsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsAdapter = NewsAdapter {
            val action = NewsListFragmentDirections.actionNewsListFragmentToNewsWebViewFragment(it)
            view.findNavController().navigate(action)

        }
        binding.recyclerView.adapter = newsAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())

         newsViewModel.getNews()

        newsViewModel.status.observe(viewLifecycleOwner, {
            when(it) {
                is NetworkStatus.Success -> {
                    binding.apply {
                        progressBar.isVisible = false
                        recyclerView.isVisible = true
                        textError.isVisible = false
                        retryButton.isVisible = false
                        errorImage.isVisible = false

                    }
                }
                is NetworkStatus.Loading -> {
                    binding.apply {
                        progressBar.isVisible = true
                        recyclerView.isVisible = false
                        textError.isVisible = false
                        retryButton.isVisible = false
                        errorImage.isVisible = false

                    }
                }
                is NetworkStatus.Error -> {
                   binding.apply {
                       progressBar.isVisible = false
                       recyclerView.isVisible = false
                       textError.isVisible = true
                       retryButton.isVisible = true
                       errorImage.isVisible = true

                   }
                }
            }
        })


        newsViewModel.articleList.observe(viewLifecycleOwner,{
            Log.i("article",it.toString())

            newsAdapter.submitList(it)
        })

        binding.retryButton.setOnClickListener {
            newsViewModel.getNews()
        }

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}