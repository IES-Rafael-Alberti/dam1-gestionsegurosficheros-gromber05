package com.dam1.data

import com.dam1.model.Usuario

class RepoUsuarios() : IRepoUsuarios {
    override var listaUsuarios: MutableList<Usuario> = mutableListOf<Usuario>()
}