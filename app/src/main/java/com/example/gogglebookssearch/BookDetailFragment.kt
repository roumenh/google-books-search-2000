package com.example.gogglebookssearch

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.gogglebookssearch.databinding.FragmentBookDetailBinding



class BookDetailFragment : Fragment() {

    private val viewModel: BooksViewModel by activityViewModels()

    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // To connect between layout
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.bookDetailFragment = this@BookDetailFragment  // it kinda works/ does not work even without this
        // check if price is available
        if (viewModel.book.value!!.saleInfo!!.listPrice != null) {
            binding.buyButton.visibility = View.VISIBLE
        }

    }


    // Function to activate the Buy link
    fun buy(id: String){
        Log.d("Opening link",id)
        // open google play store with the book :)
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(
                    "https://play.google.com/store/books/details?id=$id")
                setPackage("com.android.vending")
            }
            startActivity(intent)
    }
}