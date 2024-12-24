package app.appstocks.weatherapp.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.appstocks.weatherapp.models.response.CurrentDataResponse
import app.appstocks.weatherapp.models.response.SearchListItem
import app.appstocks.weatherapp.repository.apiservice.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    var searchQuery = mutableStateOf("")
    var citySelected =  mutableStateOf(false)

//    var originalListItem: MutableList<SearchListItem>
    var _searchListItem = MutableStateFlow<List<SearchListItem>>(emptyList())
    val searchListItem = _searchListItem.asStateFlow()
    lateinit var searchListItem2: MutableList<SearchListItem>
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

    }

    fun callSearchApi(query: String) {

        RetrofitInstance.apiService.searchApi(q = query).enqueue(object : retrofit2.Callback<List<SearchListItem>> {
            override fun onResponse(call: retrofit2.Call<List<SearchListItem>>, response: retrofit2.Response<List<SearchListItem>>) {
                if (response.isSuccessful) {
                    response.body()?.let { listItems ->
                        viewModelScope.launch(Dispatchers.Main) {
                            _searchListItem.value = emptyList()
                            searchQuery.value = query

                            println("Search result: $listItems")
                            println("Search result after prepared: $searchListItem")

                            fetchCurrentData(listItems)
                        }
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<List<SearchListItem>>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }

    fun fetchCurrentData(searchList: List<SearchListItem>) {
        for (searchItem in searchList) {
            callCurrentDataApi(searchItem)
        }
    }

    fun callCurrentDataApi(searchItem: SearchListItem) {
        RetrofitInstance.apiService.currentApi(q = searchItem.name).enqueue(object : retrofit2.Callback<CurrentDataResponse> {
            override fun onResponse(call: retrofit2.Call<CurrentDataResponse>, response: retrofit2.Response<CurrentDataResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { currentData ->
                        viewModelScope.launch(Dispatchers.Main) {
//                            searchListItem = listItems.toMutableList()
                            searchItem.currentData = currentData
                            _searchListItem.value = _searchListItem.value + searchItem
                        }
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<CurrentDataResponse>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }

    fun updateData(updatedItem: SearchListItem) {
        _searchListItem.value = _searchListItem.value.map { item ->
            if(updatedItem.id == item.id) {
                updatedItem
            } else item
        }

    }
}