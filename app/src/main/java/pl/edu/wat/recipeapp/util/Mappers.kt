package pl.edu.wat.recipeapp.util

interface OneWayMapper<T, R> {
    fun map(from: T): R
}

interface ToDomainMapper<T, R> {
    fun toDomain(from: T): R
}

interface ToEntityMapper<T, R> {
    fun toEntity(from: T): R
}

interface TwoWayMapper <T, R, W, Z> : ToDomainMapper<T, R>, ToEntityMapper<W, Z>
interface BidirectionalMapper<T, R> : TwoWayMapper<T, R, R, T>
