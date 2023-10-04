package com.davidlopez.stores

interface MainAux {
    fun hideFab(isVisible:Boolean=false)
    // actualizar la vista desde el fragment
    fun addContact(notasEntity: NotasEntity)
    fun updateContact(notasEntity: NotasEntity)
}