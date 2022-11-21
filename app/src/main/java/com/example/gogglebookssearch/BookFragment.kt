package com.example.gogglebookssearch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.gogglebookssearch.databinding.FragmentBooksListBinding
import androidx.navigation.fragment.findNavController


/**
 * A fragment representing a list of Books.
 */
class BookFragment : Fragment() {

    private val viewModel: BooksViewModel by activityViewModels()

    private var _binding: FragmentBooksListBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBooksListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // setup the recyclerView & assign to its layout manager
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //assign the adapter property
        val booksAdapter = BooksAdapter(BooksAdapter.BooksListener { books ->
            viewModel.setDetailBook(books)
            Log.d("Clicked","Clicked on ${books.volumeInfo.title}")
            findNavController().navigate(R.id.action_bookFragment_to_bookDetailFragment)

        })
        recyclerView.adapter = booksAdapter

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        viewModel.allBooks.observe(viewLifecycleOwner) { books ->
            // Update the cached copy of the cities in the adapter.
            books.let { booksAdapter.submitList(it) }
        }

        binding.viewModel = viewModel       // this is necessary so we can use viewModel variables inside this fragment..
        binding.lifecycleOwner = this       // + THIS ALSO!!! :) :) or = lifecycleOwner  (like in dashboard fragment -WHY?)
        binding.bookFragment = this@BookFragment // and this!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //-------------

    fun searchForBooks(){
        Log.d("Books","search")
        viewModel.getBooksByAuthor()
    }
}