package matheus.com.br.testemobile.others;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Matheus Alberto on 24/01/2017.
 */
public class DB extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA = "filmes";
    private static final String ID = "id";
    private static final String TITULO = "titulo";
    private static final String ANO = "ano";
    private static final String CLASSIFICACAO = "classificacao";
    private static final String LANCAMENTO = "lancamento";
    private static final String DURACAO = "duracao";
    private static final String GENERO = "genero";
    private static final String DIRETOR = "diretor";
    private static final String ESCRITOR = "escritor";
    private static final String ATORES = "atores";
    private static final String IMAGEM = "imagem";
    private static final int VERSAO = 1;

    private static String sql = "CREATE TABLE " + TABELA + "("
            + ID + " integer primary key autoincrement, "
            + TITULO + " text, "
            + ANO + " text, "
            + CLASSIFICACAO + " text, "
            + LANCAMENTO + " text, "
            + DURACAO + " text, "
            + GENERO + " text, "
            + DIRETOR + " text, "
            + ESCRITOR + " text, "
            + ATORES + " text, "
            + IMAGEM + " blob"
            + ")";

    public DB(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static int getVERSAO() {
        return VERSAO;
    }

    public static String getATORES() {
        return ATORES;
    }

    public static String getESCRITOR() {
        return ESCRITOR;
    }

    public static String getDIRETOR() {
        return DIRETOR;
    }

    public static String getGENERO() {
        return GENERO;
    }

    public static String getDURACAO() {
        return DURACAO;
    }

    public static String getLANCAMENTO() {
        return LANCAMENTO;
    }

    public static String getCLASSIFICACAO() {
        return CLASSIFICACAO;
    }

    public static String getANO() {
        return ANO;
    }

    public static String getTITULO() {
        return TITULO;
    }

    public static String getID() {
        return ID;
    }

    public static String getTABELA() {
        return TABELA;
    }

    public static String getNomeBanco() {
        return NOME_BANCO;
    }

    public static String getIMAGEM() {
        return IMAGEM;
    }
}
