package com.example.contribmontano

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MontanoViewModel: ViewModel() {

    private val repositories: MutableLiveData<List<Repository>> by lazy {
        MutableLiveData<List<Repository>>()
    }

    init {
        loadRepositories()
    }

    fun getRepositories(): LiveData<List<Repository>> {
        return repositories
    }
    private fun loadRepositories() {
        val service = GithubFactory.makeGithubService()
        viewModelScope.launch {
            val response = service.getRepos()
            repositories.value = response.body()
        }
    }
}