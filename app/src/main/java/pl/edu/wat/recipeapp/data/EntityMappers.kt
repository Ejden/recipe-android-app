package pl.edu.wat.recipeapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.edu.wat.recipeapp.data.recipe.CookingStepEntity
import pl.edu.wat.recipeapp.data.recipe.IngredientEntity
import pl.edu.wat.recipeapp.data.recipe.RecipeEntity
import pl.edu.wat.recipeapp.data.relations.RecipeWithRelations
import pl.edu.wat.recipeapp.data.relations.ShoppingListWithItems
import pl.edu.wat.recipeapp.data.shopping.ShoppingListEntity
import pl.edu.wat.recipeapp.data.shopping.ShoppingListItemEntity
import pl.edu.wat.recipeapp.domain.CookingStep
import pl.edu.wat.recipeapp.domain.CookingStepId
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

object RecipeWithRelationsBiMapper : BidirectionalMapper<RecipeWithRelations, Recipe> {
    override fun toDomain(from: RecipeWithRelations): Recipe = Recipe(
        id = RecipeId(from.recipe.id),
        name = from.recipe.name,
        difficulty = RecipeDifficulty.valueOf(from.recipe.difficulty),
        cookingTime = from.recipe.cookingTime,
        portions = from.recipe.portions,
        isFavourite = from.recipe.isFavourite,
        pricing = RecipePricing.valueOf(from.recipe.pricing),
        ingredients = from.ingredients.map { IngredientEntityMapper.toDomain(it) },
        cookingSteps = from.cookingSteps.map { CookingStepEntityMapper.toDomain(it) },
    )

    override fun toEntity(from: Recipe): RecipeWithRelations = RecipeWithRelations(
        recipe = RecipeMapper.toEntity(from),
        ingredients = from.ingredients.map { IngredientMapper(from.id).toEntity(it) },
        cookingSteps = from.cookingSteps.map { CookingStepMapper(from.id).toEntity(it) },
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

object CookingStepEntityMapper : ToDomainMapper<CookingStepEntity, CookingStep> {
    override fun toDomain(from: CookingStepEntity): CookingStep = CookingStep(
        id = CookingStepId(from.id),
        title = from.title,
        description = from.description,
    )
}

class CookingStepMapper(private val recipeId: RecipeId) :
    ToEntityMapper<CookingStep, CookingStepEntity> {

    override fun toEntity(from: CookingStep): CookingStepEntity = CookingStepEntity(
        id = from.id.raw,
        recipeId = recipeId.raw,
        title = from.title,
        description = from.description,
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
    ToDomainMapper<Flow<List<RecipeWithRelations>>, Flow<List<Recipe>>> {
    override fun toDomain(from: Flow<List<RecipeWithRelations>>): Flow<List<Recipe>> = from
        .map { list -> list.map { RecipeWithRelationsBiMapper.toDomain(it) } }
}

class ShoppingListWithItemsEntityMapper(private val recipe: RecipeWithRelations) :
    ToDomainMapper<ShoppingListWithItems, ShoppingList> {
    override fun toDomain(from: ShoppingListWithItems): ShoppingList = ShoppingList(
        id = ShoppingListId(from.shoppingList.id),
        recipe = RecipeWithRelationsBiMapper.toDomain(recipe),
        servings = from.shoppingList.servings,
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

object ShoppingListMapper : ToEntityMapper<ShoppingList, ShoppingListEntity> {
    override fun toEntity(from: ShoppingList): ShoppingListEntity = ShoppingListEntity(
        id = from.id.raw,
        recipeId = from.recipe.id.raw,
        servings = from.servings
    )
}

class ShoppingListItemMapper(private val shoppingListId: ShoppingListId) :
    ToEntityMapper<ShoppingListItem, ShoppingListItemEntity> {
    override fun toEntity(from: ShoppingListItem): ShoppingListItemEntity = ShoppingListItemEntity(
        id = from.id.raw,
        shoppingListId = shoppingListId.raw,
        checked = from.checked,
        ingredientId = from.ingredient.id.raw
    )
}
