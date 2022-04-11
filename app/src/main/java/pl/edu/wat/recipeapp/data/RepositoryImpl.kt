package pl.edu.wat.recipeapp.data

import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.edu.wat.recipeapp.data.recipe.RecipeDao
import pl.edu.wat.recipeapp.data.shopping.ShoppingListDao
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.domain.RecipeId
import pl.edu.wat.recipeapp.domain.RecipeRepository
import pl.edu.wat.recipeapp.domain.ShoppingList
import pl.edu.wat.recipeapp.domain.ShoppingListId
import pl.edu.wat.recipeapp.domain.ShoppingListItem
import pl.edu.wat.recipeapp.domain.ShoppingListItemId
import pl.edu.wat.recipeapp.domain.ShoppingListRepository
import pl.edu.wat.recipeapp.util.AsyncCallHandlers
import pl.edu.wat.recipeapp.util.CallHandlers

class RepositoryImpl(
    private val recipeRepository: RecipeDao,
    private val shoppingListRepository: ShoppingListDao
) : RecipeRepository, ShoppingListRepository {
    @Transaction
    override suspend fun insertRecipe(recipe: Recipe) = AsyncCallHandlers.handleDbCall(
        argument = recipe,
        domainMapper = RecipeWithIngredientsBiMapper
    ) {
        recipeRepository.insertRecipe(it.recipe)
        it.ingredients.forEach { ingredient -> recipeRepository.insertIngredient(ingredient) }
    }

    @Transaction
    override suspend fun findRecipe(id: RecipeId): Recipe = AsyncCallHandlers.handleDbCall(
        entityMapper = RecipeWithIngredientsBiMapper
    ) {
        recipeRepository.findRecipe(id.raw)
    }

    @Transaction
    override fun getAllRecipes(): Flow<List<Recipe>> = CallHandlers.handleDbCall(
        entityMapper = RecipeEntityFlowMapper
    ) {
        recipeRepository.getAllRecipes()
    }

    @Transaction
    override fun getAllFavouriteRecipes(): Flow<List<Recipe>> = CallHandlers.handleDbCall(
        entityMapper = RecipeEntityFlowMapper
    ) {
        recipeRepository.getAllFavouriteRecipes()
    }

    @Transaction
    override suspend fun getShoppingList(id: ShoppingListId): ShoppingList {
        val shoppingList = shoppingListRepository.getShoppingList(id.raw)
        val recipe = recipeRepository.findRecipe(shoppingList.shoppingList.recipeId)
        return ShoppingListWithItemsEntityMapper(recipe).toDomain(shoppingList)
    }

    @Transaction
    override fun getAllShoppingLists(): Flow<List<ShoppingList>> = shoppingListRepository
        .getAllShoppingLists()
        .map { list ->
            list.map {
                val recipe = recipeRepository.findRecipe(it.shoppingList.recipeId)
                ShoppingListWithItemsEntityMapper(recipe).toDomain(it)
            }
        }

    @Transaction
    override suspend fun saveShoppingList(
        shoppingList: ShoppingList
    ) = AsyncCallHandlers.handleDbCall(
        argument = shoppingList,
        domainMapper = ShoppingListMapper
    ) {
        shoppingListRepository.saveShoppingList(it)
        shoppingList.shoppingListItems.forEach { item ->
            saveShoppingListItem(
                item,
                shoppingList.id
            )
        }
    }

    @Transaction
    override suspend fun saveShoppingListItem(
        shoppingListItem: ShoppingListItem,
        shoppingListId: ShoppingListId
    ) = AsyncCallHandlers.handleDbCall(
        argument = shoppingListItem,
        domainMapper = ShoppingListItemMapper(shoppingListId)
    ) {
        shoppingListRepository.saveShoppingListItem(it)
    }

    @Transaction
    override suspend fun removeShoppingList(id: ShoppingListId) =
        shoppingListRepository.removeShoppingList(id.raw)

    @Transaction
    override suspend fun removeShoppingListItem(id: ShoppingListItemId) =
        shoppingListRepository.removeShoppingListItem(id.raw)

    @Transaction
    override suspend fun removeRecipe(id: RecipeId) =
        recipeRepository.removeRecipe(id.raw)
}
