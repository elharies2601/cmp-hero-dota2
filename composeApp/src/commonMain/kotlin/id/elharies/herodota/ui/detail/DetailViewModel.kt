package id.elharies.herodota.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.elharies.herodota.data.model.HeroRes.Companion.mapToHealthHud
import id.elharies.herodota.data.model.HeroRes.Companion.mapToListAttribute
import id.elharies.herodota.data.model.HeroRes.Companion.mapToManaHud
import id.elharies.herodota.data.model.HudModel
import id.elharies.herodota.domain.HeroRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: HeroRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailViewState> = MutableStateFlow(DetailViewState())
    val uiState: StateFlow<DetailViewState>
        get() = _uiState.asStateFlow()

    fun fetchHero(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                repository.getHeroes().stateIn(
                    viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = mutableListOf()
                ).collectLatest {
                    val hero = it.find { h -> h.id == id }
                    _uiState.value =
                        _uiState.value.copy(
                            hero = hero,
                            isLoading = false,
                            isEmpty = hero == null,
                            attributes = hero?.mapToListAttribute() ?: mutableListOf(),
                            health = hero?.mapToHealthHud()
                                ?: HudModel(),
                            mana = hero?.mapToManaHud()
                                ?: HudModel()
                        )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error"
                )
            }
        }
    }
}