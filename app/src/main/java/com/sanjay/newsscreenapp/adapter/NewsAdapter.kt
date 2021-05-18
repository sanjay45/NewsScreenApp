package com.sanjay.newsscreenapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sanjay.newsscreenapp.databinding.ItemLayoutBinding
import com.sanjay.newsscreenapp.model.Article

class NewsAdapter(private val onClick : (Article) -> Unit): ListAdapter<Article, NewsAdapter.NewsVieHolder>(NewsDiffUtilCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVieHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
                        parent,false)
        return NewsVieHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsVieHolder, position: Int) {
       val article = getItem(position)
        holder.bind(article)
    }


    inner class NewsVieHolder(private val binding:ItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root) {

            private var currentArticle:Article? = null

        init {
            itemView.setOnClickListener {
                currentArticle?.let {
                    onClick(it)
                }
            }
        }

        fun bind(article: Article) {
            currentArticle = article
            binding.apply {
                Glide.with(binding.root)
                    .load(article.imageUrl)
                    .into(itemImage)
                itemTitle.text = article.title
                itemDescription.text = article.description
            }
        }

    }
}

object NewsDiffUtilCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
       return oldItem.webUrl == newItem.webUrl
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}