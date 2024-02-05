package com.example.bancosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {
    //Comando SQL para criar a tabela Produto
    private String criaTabela = "CREATE TABLE produto (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(40) NOT NULL, " +
            "preco REAL NOT NULL, " +
            "quantidade INTEGER NOT NULL); ";

    //Context recebe o contexto da tela que irá chamar o banco de dados
    //É utilizado para enviar mensagens do banco para a tela
    //O version permite executar códigos SQL quando o banco de dados
    //for atualizado. Sempre que o número do version alterar, o método
    //onUpgrade é chamado automaticamente
    public BancoDeDados(@Nullable Context context, int version) {
        super(context, "bancoUm", null, version);
    }

    //O método é o primeiro que é executado. Usamos ele para
    //criar as tabelas iniciais do banco ou executar outros comandos
    //para deixar tudo pronto antes do primeiro uso
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(criaTabela);
    }

    //Método para cadastrar (INSERT) na tabela produto
    public boolean cadastrar(String nome, double preco, int quantidade) {

        //Criar um objeto da classe SQLiteDatabase para abrir a conexão
        SQLiteDatabase banco = getWritableDatabase();

        //Criar um objeto da classe ContentValues para passar os valores
        ContentValues valores = new ContentValues();

        //Passar o nome da coluna e o valor que será inserido
        valores.put("nome", nome); //Coluna "nome" recebe String nome
        valores.put("preco", preco);
        valores.put("quantidade", quantidade);

        //Testar o retorno do método insert()
        //O parâmetro null indica que todas as colunas da tabela irão
        //receber um valor, ou seja, nenhuma coluna ficará vazia
        if (banco.insert("produto", null, valores) != -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizar(int id, String nome, double preco, int quantidade) {

        //Criar um objeto da classe SQLiteDatabase para abrir a conexão
        SQLiteDatabase banco = getWritableDatabase();

        //Criar um objeto da classe ContentValues para passar os valores
        ContentValues valores = new ContentValues();

        //Passar o nome da coluna e o valor que será inserido
        valores.put("nome", nome); //Coluna "nome" recebe String nome
        valores.put("preco", preco);
        valores.put("quantidade", quantidade);

        //Testar o comando de atulaizar (UPDATE)
        if (banco.update("produto", valores, "_id=?", new String[]{id + ""}) != -1) {
            return true;
        } else {
            return false;
        }
    }

    //Método para apagar (DELETE) um produto
    public boolean apagar(int id) {
        SQLiteDatabase banco = getWritableDatabase();

        if (banco.delete("produto", "_id=?", new String[]{id + ""}) != 0) {
            return true;
        } else {
            return false;
        }
    }

    //Método para buscar todos os produtos
    public List<Produto> buscaTodos() {
        SQLiteDatabase banco = getWritableDatabase();
        List<Produto> lista = new ArrayList<>();

        //Para retornar valores do mesmo banco
        Cursor cursor = banco.query("produto", null,//ID
                null, //NOME
                null, //PREÇO
                null, // QUANTIDADE
                null,
                null,
                null);

        //Testar se tem algo no SELECT
        if (cursor.moveToFirst()) {
            do {

                Produto p = new Produto(cursor.getInt(0), cursor.getString(1),
                        cursor.getDouble(2), cursor.getInt(3));

                lista.add(p);
            } while (cursor.moveToNext());
        }

        return lista;
    }



    //Por padrão o SQLite não vem com as chaves estrangeiras
    //habilitadas, precisamos ativar para pode usar
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
