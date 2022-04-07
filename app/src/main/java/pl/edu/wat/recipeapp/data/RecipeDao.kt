package pl.edu.wat.recipeapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Delete
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
    @Delete
    suspend fun removeRecipe(recipe: RecipeEntity)

    @Transaction
    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun findRecipe(id: UUID): RecipeWithIngredients

    @Transaction
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<RecipeWithIngredients>>
}
