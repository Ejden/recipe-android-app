package pl.edu.wat.recipeapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.edu.wat.recipeapp.data.RecipeDatabase
import pl.edu.wat.recipeapp.data.RecipeRepositoryImpl
import pl.edu.wat.recipeapp.domain.RecipeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRecipeDatabase(app: Application): RecipeDatabase {
        return Room.databaseBuilder(app, RecipeDatabase::class.java, "recipe_db").build()
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(db: RecipeDatabase): RecipeRepository {
        return RecipeRepositoryImpl(db.repository)
    }
}
