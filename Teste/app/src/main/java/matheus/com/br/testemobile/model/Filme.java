package matheus.com.br.testemobile.model;

import java.net.URL;

/**
 * Created by Matheus Alberto on 24/01/2017.
 */
public class Filme {

    private Integer id;
    private String titulo;
    private String ano;
    private String classificacao;
    private String lancamento;
    private String duracao;
    private String genero;
    private String diretor;
    private String escritor;
    private String atores;
    private byte[] imagem;

    public Filme(){

    }

    public Filme(Integer id, String titulo, String ano, String classificacao, String lancamento, String duracao, String genero, String diretor, String escritor, String atores, byte[] imagem) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.classificacao = classificacao;
        this.lancamento = lancamento;
        this.duracao = duracao;
        this.genero = genero;
        this.diretor = diretor;
        this.escritor = escritor;
        this.atores = atores;
        this.imagem = imagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getEscritor() {
        return escritor;
    }

    public void setEscritor(String escritor) {
        this.escritor = escritor;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
