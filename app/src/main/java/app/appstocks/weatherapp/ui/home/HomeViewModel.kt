package app.appstocks.weatherapp.ui.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.appstocks.domain.usecases.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import app.appstocks.domain.utils.Result
import javax.inject.Inject
import app.appstocks.domain.SearchListItem
import app.appstocks.domain.CurrentDataResponse

@HiltViewModel
class HomeViewModel @Inject constructor():  ViewModel() {

    @Inject
    lateinit var weatherUseCase: WeatherUseCase

    var searchQuery = mutableStateOf("")
    var citySelected =  mutableStateOf(false)

//    var originalListItem: MutableList<SearchListItem>
    var _searchListItem = MutableStateFlow<List<SearchListItem>>(emptyList())
    val searchListItem = _searchListItem.asStateFlow()
//    lateinit var searchListItem2: MutableList<app.appstocks.domain.SearchListItem>
    var selectedItem: SearchListItem? = null
    init {
//        originalListItem = getSearchList()
//        searchListItem = mutableListOf()
    }

    /*fun getSearchList(): MutableList<SearchListItem> {
        return mutableListOf(SearchListItem(
            id= 1104989,
            name= "Ahmedabad",
            region= "Gujarat",
            country="India",
            lat= 23.03,
            lon= 72.62,
            url= "ahmedabad-gujarat-india"
        ), SearchListItem(
            id= 1104989,
            name= "Delhi",
            region= "Gujarat",
            country="India",
            lat= 23.03,
            lon= 72.62,
            url= "ahmedabad-gujarat-india"
        ), SearchListItem(
            id= 1104989,
            name= "Gurugram",
            region= "Gujarat",
            country="India",
            lat= 23.03,
            lon= 72.62,
            url= "ahmedabad-gujarat-india"
        ), SearchListItem(
            id= 1104989,
            name= "Pune",
            region= "Gujarat",
            country="India",
            lat= 23.03,
            lon= 72.62,
            url= "ahmedabad-gujarat-india"
        ) )
    }*/

    fun onSearchText(str: String): Unit {

        /*if(searchQuery.value.isEmpty()) {
            searchListItem.clear()
        } else {
            getFilterResult(searchQuery.value)
        }*/

        selectedItem = null
        citySelected.value = false
        searchQuery.value = str
        viewModelScope.launch {
            callSearchApi(str)
            println("Search query: "+str)
        }

    }

    fun onSearchItemClick(item: SearchListItem): Unit {
        selectedItem = item
        citySelected.value = true
    }

    fun getFilterResult(query: String) {
//        searchListItem = originalListItem.filter { it.name.lowercase(Locale.getDefault()).contains(query) }.toMutableList()
    }

    fun backPress() {
        if(citySelected.value) {
            selectedItem = null
            citySelected.value = false
            searchQuery.value = ""
        }
    }

    suspend fun callSearchApi(query: String) {
        val result = weatherUseCase.searchQuery(query = query)
        when(result) {
            is Result.Success -> {
                Log.e("Api response: ", result.data.joinToString())

                _searchListItem.value = emptyList()
                searchQuery.value = query

                fetchCurrentData(result.data)
            }
            is Result.Error -> {
                result.error.message?.let { Log.e("Api response: Exception", it) }
            }
        }
    }

    fun fetchCurrentData(searchList: List<SearchListItem>) {
        for (searchItem in searchList) {
            callCurrentDataApi(searchItem)
        }
    }

    fun callCurrentDataApi(searchItem: SearchListItem) {
        viewModelScope.launch {
            val result = weatherUseCase.currentApi(query = searchItem.name)
            when(result) {
                is Result.Success -> {
//                    searchListItem = listItems.toMutableList()
                    searchItem.currentData = result.data
                    _searchListItem.value = _searchListItem.value + searchItem
                }
                is Result.Error -> {
                    result.error.message?.let { Log.e("Api response: Exception", it) }
                }
            }
        }
    }

    fun updateData(updatedItem: SearchListItem) {
        _searchListItem.value = _searchListItem.value.map { item ->
            if(updatedItem.id == item.id) {
                updatedItem
            } else item
        }

    }
}