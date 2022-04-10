package pl.edu.wat.recipeapp.data.shopping

import androidx.room.Dao
import androidx.room.Delete
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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveShoppingList(shoppingList: ShoppingListEntity)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveShoppingListItem(shoppingListItem: ShoppingListItemEntity)

    @Transaction
    @Delete
    suspend fun removeShoppingList(shoppingList: ShoppingListEntity)

    @Transaction
    @Delete
    suspend fun removeShoppingListItem(shoppingListItem: ShoppingListItemEntity)
}
