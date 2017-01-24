package matheus.com.br.testemobile.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import matheus.com.br.testemobile.R;
import matheus.com.br.testemobile.model.Filme;

/**
 * Created by Matheus Alberto on 24/01/2017.
 */
public class FilmeAdapter extends BaseAdapter {

    private static Context ctx;
    private static List<Filme> filmes;

    public FilmeAdapter(Context ctx, List<Filme> filmes){
        this.ctx = ctx;
        this.filmes = filmes;
    }

    @Override
    public int getCount() {
        return filmes.size();
    }

    @Override
    public Object getItem(int position) {
        return filmes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Filme f = (Filme)getItem(position);

        LayoutInflater layout = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layout.inflate(R.layout.grid_filme, null);

        ImageView poster = (ImageView) view.findViewById(R.id.imgFilme);
        byte[] fotoArray = f.getImagem();
        if(fotoArray != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(fotoArray, 0, fotoArray.length);
            poster.setImageBitmap(bitmap);
        }

        TextView titulo = (TextView) view.findViewById(R.id.txtTitulo);
        titulo.setText(f.getTitulo());

        TextView ano = (TextView) view.findViewById(R.id.txtAno);
        ano.setText("Ano: " + f.getAno());

        TextView classif = (TextView) view.findViewById(R.id.txtClassificacao);
        classif.setText("Classificação: " + f.getClassificacao());

        TextView estreia = (TextView) view.findViewById(R.id.txtLancamento);
        estreia.setText("Estréia: " + f.getLancamento());

        TextView duracao = (TextView) view.findViewById(R.id.txtDuracao);
        duracao.setText("Duração: " + f.getDuracao());

        TextView genero = (TextView) view.findViewById(R.id.txtGenero);
        genero.setText("Gênero: " + f.getGenero());

        TextView diretor = (TextView) view.findViewById(R.id.txtDiretor);
        diretor.setText("Diretor: " + f.getDiretor());

        TextView escritor = (TextView) view.findViewById(R.id.txtEscritor);
        escritor.setText("Escrito por: " + f.getEscritor());

        TextView atores = (TextView) view.findViewById(R.id.txtAtores);
        atores.setText("Atores: " + f.getAtores());

        return view;
    }
}
