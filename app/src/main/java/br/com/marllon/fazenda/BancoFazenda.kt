package br.com.marllon.fazenda

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoFazenda(context : Context) : SQLiteOpenHelper(context, "fazenda.db", null, 1){

    companion object {
        const val NOME_TABELA = "Fazenda"
        const val REGISTRO = "registro"
        const val NOME = "nome"
        const val VALOR = "valor"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql_criacao = "CREATE TABLE $NOME_TABELA (" +
                "$REGISTRO TEXT PRIMARY KEY," +
                "$NOME TEXT," +
                "$VALOR REAL," +
                "$LATITUDE REAL," +
                "$LONGITUDE REAL)"
        db.execSQL(sql_criacao)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql_exclusao = "DROP TABLE IF EXISTS $NOME_TABELA"
        db.execSQL(sql_exclusao)
        onCreate(db)
    }

}