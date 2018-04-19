package com.example.utilizador.yt;

/**
 * Created by Utilizador on 14/04/2018.
 */

public class Video {

    public String getVideoID() {
        return VideoID;
    }

    public void setVideoID(String videoID) {
        VideoID = videoID;
    }

    public String VideoID;
    public String nome;
    public String duracao;
    public String dataCreated;
    public String author;


    public Video(String VideoID,String nome,String dataCreated,String author)
    {
        this.nome = nome;
        this.dataCreated = dataCreated;
        this.author = author;
        this.VideoID = VideoID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDataCreated() {
        return dataCreated;
    }

    public void setDataCreated(String dataCreated) {
        this.dataCreated = dataCreated;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String ToString(){
        return nome;
    }
}
