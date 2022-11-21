package com.example.gogglebookssearch


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gogglebookssearch.network.Book
import com.example.gogglebookssearch.network.BookApi
import kotlinx.coroutines.launch

class BooksViewModel (): ViewModel(){      // repository

    private val _book = MutableLiveData<Book.Item>()
    var book : LiveData<Book.Item> = _book

    val searchText = MutableLiveData<String>()

    private val _allBooks = MutableLiveData<List<Book.Item>>()
    var allBooks : LiveData<List<Book.Item>> = _allBooks


    fun getBooksByAuthor(){
        Log.d("Getting books",searchText.value!!)
        viewModelScope.launch {
            try {
                val result = BookApi.retrofitService.getBooks("inauthor:${searchText.value!!}")

                val first_book =  result.items[0].volumeInfo.title
                Log.d("Retrofit",first_book)
                Log.d("Retrofit",result.totalItems.toString())

                _allBooks.value = result.items

            }catch (e: Exception){
                Log.d("Retrofit","Error : ${e.message}")
            }
        }
    }

    init {
        Log.d("viewModel","Init")
        searchText.value = ""
    }


    // Function to set the _book value to the selected one
    fun setDetailBook(selectedBook: Book.Item){
        _book.value = selectedBook
        Log.d("New book",book.value!!.volumeInfo.title)
    }




}

