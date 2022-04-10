package pl.edu.wat.recipeapp.util

object AsyncCallHandlers {
    suspend fun <T, R> handleDbCall(
        entityMapper: ToDomainMapper<T, R>,
        call: suspend () -> T
    ): R {
        return entityMapper.toDomain(call())
    }

    suspend fun <T, R> handleDbCall(
        argument: T,
        domainMapper: ToEntityMapper<T, R>,
        call: suspend (arg: R) -> Unit
    ) {
        call(domainMapper.toEntity(argument))
    }
}

object CallHandlers {
    fun <T, R> handleDbCall(
        entityMapper: ToDomainMapper<T, R>,
        call: () -> T
    ): R {
        return entityMapper.toDomain(call())
    }

    fun <T, R> handleDbCall(
        argument: T,
        domainMapper: OneWayMapper<T, R>,
        call: (arg: R) -> Unit
    ) {
        call(domainMapper.map(argument))
    }
}
