package pl.edu.wat.recipeapp.data

import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.edu.wat.recipeapp.data.recipe.IngredientEntity
import pl.edu.wat.recipeapp.data.recipe.RecipeDao
import pl.edu.wat.recipeapp.data.recipe.RecipeEntity
import pl.edu.wat.recipeapp.data.relations.RecipeWithIngredients
import pl.edu.wat.recipeapp.data.relations.ShoppingListWithItems
import pl.edu.wat.recipeapp.data.shopping.ShoppingListDao
import pl.edu.wat.recipeapp.data.shopping.ShoppingListEntity
import pl.edu.wat.recipeapp.data.shopping.ShoppingListItemEntity
import pl.edu.wat.recipeapp.domain.Ingredient
import pl.edu.wat.recipeapp.domain.IngredientId
import pl.edu.wat.recipeapp.domain.MeasurementUnit
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.domain.RecipeId
import pl.edu.wat.recipeapp.domain.RecipePricing
import pl.edu.wat.recipeapp.domain.RecipeRepository
import pl.edu.wat.recipeapp.domain.ShoppingList
import pl.edu.wat.recipeapp.domain.ShoppingListId
import pl.edu.wat.recipeapp.domain.ShoppingListItem
import pl.edu.wat.recipeapp.domain.ShoppingListItemId
import pl.edu.wat.recipeapp.domain.ShoppingListRepository

class RepositoryImpl(
    private val recipeRepository: RecipeDao,
    private val shoppingListRepository: ShoppingListDao
) : RecipeRepository, ShoppingListRepository {
    @Transaction
    override suspend fun insertRecipe(recipe: Recipe) {
        recipeRepository.insertRecipe(recipe.toEntity())
        recipe.ingredients
            .map { it.toEntity(recipe.id) }
            .forEach { recipeRepository.insertIngredient(it) }
    }

    @Transaction
    override suspend fun removeRecipe(recipe: Recipe) = recipeRepository
        .removeRecipe(recipe.toEntity())

    @Transaction
    override suspend fun findRecipe(id: RecipeId): Recipe = AsyncCallHandlers.handleDbCall(
        domainMapper = RecipeEntityMapper,
        call = { recipeRepository.findRecipe(id.raw) }
    )

    @Transaction
    override fun getAllRecipes(): Flow<List<Recipe>> = recipeRepository
        .getAllRecipes()
        .map { list -> list.map { it.toDomain() } }

    override suspend fun getShoppingList(id: ShoppingListId): ShoppingList {
        val shoppingList = shoppingListRepository.getShoppingList(id.raw)
        val recipe = recipeRepository.findRecipe(shoppingList.shoppingList.recipeId)
        return shoppingList.toDomain(recipe)
    }

    override fun getAllShoppingLists(): Flow<List<ShoppingList>> = shoppingListRepository
        .getAllShoppingLists()
        .map { list -> list.map { it.toDomain(recipeRepository.findRecipe(it.shoppingList.recipeId)) } }

    override suspend fun saveShoppingList(shoppingList: ShoppingList) {
        shoppingList.shoppingListItems.forEach { item ->
            saveShoppingListItem(item, shoppingList.id)
        }
        shoppingListRepository.saveShoppingList(shoppingList.toEntity())
    }

    override suspend fun saveShoppingListItem(
        shoppingListItem: ShoppingListItem,
        shoppingListId: ShoppingListId
    ) {
        shoppingListRepository.saveShoppingListItem(shoppingListItem.toEntity(shoppingListId))
    }

    override suspend fun removeShoppingList(shoppingList: ShoppingList) {
        shoppingListRepository.removeShoppingList(shoppingList.toEntity())
    }

    override suspend fun removeShoppingListItem(
        shoppingListItem: ShoppingListItem,
        shoppingListId: ShoppingListId
    ) {
        shoppingListRepository.removeShoppingListItem(shoppingListItem.toEntity(shoppingListId))
    }
}

private fun ShoppingList.toEntity() = ShoppingListEntity(
    id = id.raw,
    recipeId = recipe.id.raw
)

private fun ShoppingListWithItems.toDomain(recipe: RecipeWithIngredients) = ShoppingList(
    id = ShoppingListId(shoppingList.id),
    recipe = recipe.toDomain(),
    shoppingListItems = shoppingListItems.map { item ->
        ShoppingListItem(
            id = ShoppingListItemId(item.id),
            ingredient = recipe.ingredients.first { item.ingredientId == it.id }.toDomain(),
            checked = item.checked
        )
    }
)

private fun ShoppingListItem.toEntity(shoppingListId: ShoppingListId) = ShoppingListItemEntity(
    id = id.raw,
    shoppingListId = shoppingListId.raw,
    ingredientId = ingredient.id.raw,
    checked = checked
)

private fun Recipe.toEntity() = RecipeEntity(
    id = id.raw,
    name = name,
    difficulty = difficulty.name,
    cookingTime = cookingTime,
    portions = portions,
    isFavourite = isFavourite,
    pricing = pricing.name
)

private fun Ingredient.toEntity(recipeId: RecipeId) = IngredientEntity(
    id = id.raw,
    recipeId = recipeId.raw,
    name = name,
    quantity = quantity,
    unit = unit.name
)

private fun RecipeWithIngredients.toDomain() = Recipe(
    id = RecipeId(recipe.id),
    name = recipe.name,
    difficulty = RecipeDifficulty.valueOf(recipe.difficulty),
    cookingTime = recipe.cookingTime,
    portions = recipe.portions,
    isFavourite = recipe.isFavourite,
    pricing = RecipePricing.valueOf(recipe.pricing),
    ingredients = ingredients.map { it.toDomain() }
)

private fun IngredientEntity.toDomain() = Ingredient(
    id = IngredientId(id),
    name = name,
    quantity = quantity,
    unit = MeasurementUnit.valueOf(unit)
)
