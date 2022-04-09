package pl.edu.wat.recipeapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.edu.wat.recipeapp.data.Database
import pl.edu.wat.recipeapp.data.RepositoryImpl
import pl.edu.wat.recipeapp.domain.RecipeRepository
import pl.edu.wat.recipeapp.domain.ShoppingListRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRecipeDatabase(app: Application): Database {
        return Room.databaseBuilder(app, Database::class.java, "recipe_db").build()
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(db: Database): RecipeRepository {
        return RepositoryImpl(db.recipeDao, db.shoppingListDao)
    }

    @Provides
    @Singleton
    fun provideShoppingListRepository(db: Database): ShoppingListRepository {
        return RepositoryImpl(db.recipeDao, db.shoppingListDao)
    }
}
