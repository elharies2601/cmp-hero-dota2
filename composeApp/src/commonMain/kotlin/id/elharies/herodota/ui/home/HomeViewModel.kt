package id.elharies.herodota.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.elharies.herodota.data.model.HeroRes
import id.elharies.herodota.domain.HeroRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HeroRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState())
    val uiState: StateFlow<HomeViewState>
        get() = _uiState.asStateFlow()

    fun fetchHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                repository.getHeroes().stateIn(
                    viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = mutableListOf()
                ).collectLatest {
                    _uiState.value = _uiState.value.copy(isLoading = false, data = it, isEmpty = it.isEmpty())
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(errorMessage = e.message ?: "")
            }
        }
    }
}