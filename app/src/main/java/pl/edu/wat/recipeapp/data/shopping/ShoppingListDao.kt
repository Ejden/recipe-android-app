package pl.edu.wat.recipeapp.data.shopping

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import pl.edu.wat.recipeapp.data.relations.ShoppingListWithItems
import java.util.UUID

@Dao
interface ShoppingListDao {
    @Transaction
    @Query("SELECT * FROM shopping_lists WHERE id = :id")
    suspend fun getShoppingList(id: UUID): ShoppingListWithItems

    @Transaction
    @Query("SELECT * FROM shopping_lists")
    fun getAllShoppingLists(): Flow<List<ShoppingListWithItems>>

    @Transaction
    @Query("SELECT * FROM shopping_lists WHERE recipeId = :recipeId")
    fun getAllShoppingListsForRecipe(recipeId: UUID): Flow<List<ShoppingListWithItems>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveShoppingList(shoppingList: ShoppingListEntity)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveShoppingListItem(shoppingListItem: ShoppingListItemEntity)

    @Transaction
    @Query("DELETE FROM shopping_lists WHERE id = :shoppingListId")
    suspend fun removeShoppingList(shoppingListId: UUID)

    @Transaction
    @Query("DELETE FROM shopping_list_items WHERE id = :shoppingListItemId")
    suspend fun removeShoppingListItem(shoppingListItemId: UUID)
}
