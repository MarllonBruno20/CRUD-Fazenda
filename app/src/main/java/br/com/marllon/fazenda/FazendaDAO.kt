package br.com.marllon.fazenda

import android.content.ContentValues
import android.content.Context
import android.util.Log
import java.io.File

class FazendaDAO(context: Context) {

    val meuBanco = BancoFazenda(context)

    fun insert(fazenda: Fazenda): Boolean {
        val db = meuBanco.writableDatabase
        val cv = ContentValues()
        cv.put("registro", fazenda.registro)
        cv.put("nome", fazenda.nome)
        cv.put("valor", fazenda.valor)
        cv.put("latitude", fazenda.latitude)
        cv.put("longitude", fazenda.longitude)

        //nullColumnHack -> Sometimes you want to insert an empty row, in that case ContentValues have no content value, and you should use nullColumnHack.
        val resultado = db?.insert("Fazenda", null, cv)
        Log.i("Teste","Inserção: " + resultado)

        if (resultado == -1L) {
            throw InsercaoBancoException("Erro ao inserir fazenda no banco de dados.")
        }

        return true
    }

    fun listar(): MutableList<Fazenda> {
        val lista = mutableListOf<Fazenda>()
        val db = meuBanco.readableDatabase
        val sql = "SELECT * FROM Fazenda"
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val registro = cursor.getString(cursor.getColumnIndexOrThrow("registro"))
                val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
                val valor = cursor.getFloat(cursor.getColumnIndexOrThrow("valor"))
                val latitude = cursor.getFloat(cursor.getColumnIndexOrThrow("latitude"))
                val longitude = cursor.getFloat(cursor.getColumnIndexOrThrow("longitude"))
                val fazenda = Fazenda(registro, nome, valor, latitude, longitude)
                lista.add(fazenda)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    fun alterar(registro : String, nome : String) : Boolean{
        val db = meuBanco.writableDatabase
        val cv  = ContentValues()
        cv.put("nome", nome)
        val linhasAfetadas = db.update("Fazenda", cv, "registro = ?", arrayOf(registro))
        db.close()

        return linhasAfetadas > 0
    }

    fun deletar(registro : String) : Boolean{
        val db = meuBanco.writableDatabase
        val linhasAfetadas = db.delete("Fazenda", "registro = ?", arrayOf(registro))
        db.close()
        return linhasAfetadas > 0
    }

    fun fazerBackup(context: Context) : Pair<Boolean, String?>{
        val db = meuBanco.readableDatabase
        try {
            val query = "SELECT * FROM Fazenda"
            val cursor = db.rawQuery(query, null)
            val backupBuilder = StringBuilder()

            while (cursor.moveToNext()) {
                val registro = cursor.getString(cursor.getColumnIndexOrThrow("registro"))
                val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
                val valor = cursor.getDouble(cursor.getColumnIndexOrThrow("valor"))
                val latitude = cursor.getDouble(cursor.getColumnIndexOrThrow("latitude"))
                val longitude = cursor.getDouble(cursor.getColumnIndexOrThrow("longitude"))

                backupBuilder.append("Registro: $registro, Nome: $nome, Valor: $valor, Latitude: $latitude, Longitude: $longitude\n")
            }

            cursor.close()

            //Pegando a string de resultado e escrevendo em um arquivo
            val backupString = backupBuilder.toString()
            val file = File(context.filesDir, "backup_fazendas.txt")
            file.writeText(backupString)

            return true to null
        } catch (e: InsercaoBancoException) {
            return false to e.message
        } finally {
            db.close()
        }
    }
}