package ru.perm.school9.gate.services

interface Factory<PRODUCT> {
    fun produce() : PRODUCT
}