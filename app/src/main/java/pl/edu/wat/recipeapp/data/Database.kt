package pl.edu.wat.recipeapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.edu.wat.recipeapp.data.recipe.CookingStepEntity
import pl.edu.wat.recipeapp.data.recipe.IngredientEntity
import pl.edu.wat.recipeapp.data.recipe.RecipeDao
import pl.edu.wat.recipeapp.data.recipe.RecipeEntity
import pl.edu.wat.recipeapp.data.shopping.ShoppingListDao
import pl.edu.wat.recipeapp.data.shopping.ShoppingListEntity
import pl.edu.wat.recipeapp.data.shopping.ShoppingListItemEntity

@Database(
    entities = [
        RecipeEntity::class,
        CookingStepEntity::class,
        IngredientEntity::class,
        ShoppingListEntity::class,
        ShoppingListItemEntity::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val recipeDao: RecipeDao
    abstract val shoppingListDao: ShoppingListDao
}
