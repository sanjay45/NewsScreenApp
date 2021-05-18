package com.sanjay.newsscreenapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.sanjay.newsscreenapp.databinding.FragmentNewsWebViewBinding


class NewsWebViewFragment : Fragment() {

    private var _binding: FragmentNewsWebViewBinding? = null
    private val binding get() = _binding!!

    private val args: NewsWebViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsWebViewBinding.
        inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article
       binding.webView.apply {
           webViewClient = object: WebViewClient() {
               override fun onPageFinished(view: WebView?, url: String?) {
                   super.onPageFinished(view, url)
                   binding.progressBar.isVisible = false
                   binding.webView.isVisible = true
               }
           }
           loadUrl(article?.webUrl)
       }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}