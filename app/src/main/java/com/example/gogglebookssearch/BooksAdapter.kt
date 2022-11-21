package com.example.gogglebookssearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gogglebookssearch.network.Book
import com.example.gogglebookssearch.databinding.FragmentBookBinding



class BooksAdapter (val clickListener: BooksListener) :
    ListAdapter<Book.Item, BooksAdapter.BooksViewHolder>(DiffCallback){

    // Viewholder will allow to access views created from layout file in code
    class BooksViewHolder(private var binding: FragmentBookBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: BooksListener, books: Book.Item){
            binding.bookitem = books  // this won't work without the <data> tag in fragment_book.xaml
            binding.clickListener = clickListener // without this, the click listener below and defined in fragment_book.xaml won't work
            binding.executePendingBindings()  // not sure what this is good for
        }
    }

    // inflate the layout....
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val viewHolder = BooksViewHolder(
            FragmentBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return viewHolder
    }

    //override the onBindViewHolder() to bind the view at the specified position
    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }

    // lets try to setup a onclick listener first...
    class BooksListener (val clickListener: (books: Book.Item) -> Unit) {
        fun onClick(books: Book.Item) = clickListener(books)
    }

    /*
    DiffCallBack is an object that helps the ListAdapter determine which items in the new
    and old lists are different when updating the list.
    */
    companion object{
        private val DiffCallback = object : DiffUtil.ItemCallback<Book.Item>(){

            override fun areItemsTheSame(oldItem: Book.Item, newItem:  Book.Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem:  Book.Item, newItem:  Book.Item): Boolean {
                return oldItem == newItem
            }
        }
    }
}