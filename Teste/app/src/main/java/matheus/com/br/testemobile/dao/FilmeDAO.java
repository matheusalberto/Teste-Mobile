package matheus.com.br.testemobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import matheus.com.br.testemobile.others.DB;
import matheus.com.br.testemobile.model.Filme;

/**
 * Created by Matheus Alberto on 24/01/2017.
 */
public class FilmeDAO {

    private static String tableName = "filmes";
    private static Context ctx;
    private static String[] columns = {"id", "titulo", "ano", "classificacao", "lancamento", "duracao", "genero", "diretor", "escritor", "atores", "imagem"};

    public FilmeDAO(Context ctx){
        this.ctx = ctx;
    }

    public String inserir(Filme filme){
        SQLiteDatabase db = new DB(ctx).getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("titulo", filme.getTitulo());
        cv.put("ano", filme.getAno());
        cv.put("classificacao", filme.getClassificacao());
        cv.put("lancamento", filme.getLancamento());
        cv.put("duracao", filme.getDuracao());
        cv.put("genero", filme.getGenero());
        cv.put("diretor", filme.getDiretor());
        cv.put("escritor", filme.getEscritor());
        cv.put("atores", filme.getAtores());
        cv.put("imagem", filme.getImagem());

        long resp = db.insert("filmes", null, cv);

        if(resp == -1){
            return "Erro na inserção";
        } else {
            return "Filme inserido nos favoritos";
        }
    }

    public Boolean deletar(Filme filme){
        SQLiteDatabase db = new DB(ctx).getWritableDatabase();
        String[] id = new String[]{filme.getId().toString()};
        int result = db.delete(tableName, "id=?", id);
        boolean x = (result) > 0;
        return x;
    }

    public Boolean procurarPorNome(String nome){
        SQLiteDatabase db = new DB(ctx).getWritableDatabase();

        Cursor rs = db.query("filmes", columns, "titulo=?", new String[]{nome}, null, null, null);
        Filme f = null;

        if(rs.moveToFirst()){
            return true;
        }
        return false;
    }

    public List<Filme> carregar(){
        SQLiteDatabase db = new DB(ctx).getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM filmes", null);
        List<Filme> lista = new ArrayList<>();

        while(cursor.moveToNext()){
            Filme f = new Filme(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getBlob(10));
            lista.add(f);
        }
        return lista;
    }

}
