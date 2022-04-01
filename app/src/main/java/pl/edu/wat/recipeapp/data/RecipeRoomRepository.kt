package pl.edu.wat.recipeapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface RecipeRoomRepository {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun removeRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM recipeentity WHERE id = :id")
    suspend fun findRecipe(id: UUID): RecipeEntity

    @Query("SELECT * FROM recipeentity")
    fun getAllRecipes(): Flow<List<RecipeEntity>>
}
