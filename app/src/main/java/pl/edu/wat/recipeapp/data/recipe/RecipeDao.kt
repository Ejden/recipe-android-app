package pl.edu.wat.recipeapp.data.recipe

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import pl.edu.wat.recipeapp.data.relations.RecipeWithIngredients
import java.util.UUID

@Dao
interface RecipeDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredient: IngredientEntity)

    @Transaction
    @Query("DELETE FROM recipes WHERE id = :recipeId")
    suspend fun removeRecipe(recipeId: UUID)

    @Transaction
    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun findRecipe(id: UUID): RecipeWithIngredients

    @Transaction
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<RecipeWithIngredients>>

    @Transaction
    @Query("SELECT * FROM recipes WHERE isFavourite = 1")
    fun getAllFavouriteRecipes(): Flow<List<RecipeWithIngredients>>
}
