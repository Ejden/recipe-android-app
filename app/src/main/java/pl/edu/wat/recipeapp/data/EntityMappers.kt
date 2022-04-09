package pl.edu.wat.recipeapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.edu.wat.recipeapp.data.recipe.IngredientEntity
import pl.edu.wat.recipeapp.data.recipe.RecipeEntity
import pl.edu.wat.recipeapp.data.relations.RecipeWithIngredients
import pl.edu.wat.recipeapp.data.relations.ShoppingListWithItems
import pl.edu.wat.recipeapp.domain.Ingredient
import pl.edu.wat.recipeapp.domain.IngredientId
import pl.edu.wat.recipeapp.domain.MeasurementUnit
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.domain.RecipeId
import pl.edu.wat.recipeapp.domain.RecipePricing
import pl.edu.wat.recipeapp.domain.ShoppingList
import pl.edu.wat.recipeapp.domain.ShoppingListId
import pl.edu.wat.recipeapp.domain.ShoppingListItem
import pl.edu.wat.recipeapp.domain.ShoppingListItemId
import pl.edu.wat.recipeapp.util.BidirectionalMapper
import pl.edu.wat.recipeapp.util.ToDomainMapper
import pl.edu.wat.recipeapp.util.ToEntityMapper

object RecipeWithIngredientsBiMapper : BidirectionalMapper<RecipeWithIngredients, Recipe> {
    override fun toDomain(from: RecipeWithIngredients): Recipe = Recipe(
        id = RecipeId(from.recipe.id),
        name = from.recipe.name,
        difficulty = RecipeDifficulty.valueOf(from.recipe.difficulty),
        cookingTime = from.recipe.cookingTime,
        portions = from.recipe.portions,
        isFavourite = from.recipe.isFavourite,
        pricing = RecipePricing.valueOf(from.recipe.pricing),
        ingredients = from.ingredients.map { IngredientEntityMapper.toDomain(it) }
    )

    override fun toEntity(from: Recipe): RecipeWithIngredients = RecipeWithIngredients(
        recipe = RecipeMapper.toEntity(from),
        ingredients = from.ingredients.map { IngredientMapper(from.id).toEntity(it) }
    )
}

object IngredientEntityMapper : ToDomainMapper<IngredientEntity, Ingredient> {
    override fun toDomain(from: IngredientEntity): Ingredient = Ingredient(
        id = IngredientId(from.id),
        name = from.name,
        quantity = from.quantity,
        unit = MeasurementUnit.valueOf(from.unit)
    )
}

class IngredientMapper(private val recipeId: RecipeId) :
    ToEntityMapper<Ingredient, IngredientEntity> {
    override fun toEntity(from: Ingredient): IngredientEntity = IngredientEntity(
        id = from.id.raw,
        recipeId = recipeId.raw,
        name = from.name,
        quantity = from.quantity,
        unit = from.unit.name
    )
}

object RecipeMapper : ToEntityMapper<Recipe, RecipeEntity> {
    override fun toEntity(from: Recipe): RecipeEntity = RecipeEntity(
        id = from.id.raw,
        name = from.name,
        difficulty = from.difficulty.name,
        cookingTime = from.cookingTime,
        portions = from.portions,
        isFavourite = from.isFavourite,
        pricing = from.pricing.name
    )
}

object RecipeEntityFlowMapper :
    ToDomainMapper<Flow<List<RecipeWithIngredients>>, Flow<List<Recipe>>> {
    override fun toDomain(from: Flow<List<RecipeWithIngredients>>): Flow<List<Recipe>> = from
        .map { list -> list.map { RecipeWithIngredientsBiMapper.toDomain(it) } }
}

class ShoppingListWithItemsEntityMapper(private val recipe: RecipeWithIngredients) :
    ToDomainMapper<ShoppingListWithItems, ShoppingList> {
    override fun toDomain(from: ShoppingListWithItems): ShoppingList = ShoppingList(
        id = ShoppingListId(from.shoppingList.id),
        recipe = RecipeWithIngredientsBiMapper.toDomain(recipe),
        shoppingListItems = from.shoppingListItems.map { item ->
            ShoppingListItem(
                id = ShoppingListItemId(item.id),
                ingredient = IngredientEntityMapper.toDomain(
                    recipe.ingredients.first { item.ingredientId == it.id }
                ),
                checked = item.checked
            )
        }
    )
}
