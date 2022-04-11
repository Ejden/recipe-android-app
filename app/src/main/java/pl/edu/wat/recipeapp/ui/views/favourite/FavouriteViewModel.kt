package pl.edu.wat.recipeapp.ui.views.favourite

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.edu.wat.recipeapp.domain.RecipeRepository
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    repository: RecipeRepository
) : ViewModel() {

    val favourites = repository.getAllFavouriteRecipes()



}