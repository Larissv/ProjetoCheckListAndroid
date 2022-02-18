package com.cursoandroid.projetochecklistandroid.model;

import android.text.Editable;

import java.io.Serializable;

public class CheckList implements Serializable {
    private int id;
    private String saidaRetorno;
    private String dataC;
    private String hora;
    private String placa;
    private String motorista;
    private String km;
    private String tracao;
    private String calibragemPneu;
    private String estepe;
    private String freioDianteiro;
    private String freioTraseiro;
    private String balanceamento;
    private String limpezaRadiador;
    private String oleoMotor;
    private String filtroOleo;
    private String paraChoqueDianteiro;
    private String paraChoqueTraseiro;
    private String placasCaminhao;
    private String cintoSeguranca;
    private String pedais;
    private String aberturaPortas;

    public CheckList(String saidaRetorno, String dataC, String hora, String placa,
                     String motorista, String km, String tracao, String calibragemPneu,
                     String estepe, String freioDianteiro, String freioTraseiro, String balanceamento,
                     String limpezaRadiador, String oleoMotor, String filtroOleo,
                     String paraChoqueDianteiro, String paraChoqueTraseiro, String placasCaminhao,
                     String cintoSeguranca, String pedais, String aberturaPortas) {
        this.saidaRetorno = saidaRetorno;
        this.dataC = dataC;
        this.hora = hora;
        this.placa = placa;
        this.motorista = motorista;
        this.km = km;
        this.tracao = tracao;
        this.calibragemPneu = calibragemPneu;
        this.estepe = estepe;
        this.freioDianteiro = freioDianteiro;
        this.freioTraseiro = freioTraseiro;
        this.balanceamento = balanceamento;
        this.limpezaRadiador = limpezaRadiador;
        this.oleoMotor = oleoMotor;
        this.filtroOleo = filtroOleo;
        this.paraChoqueDianteiro = paraChoqueDianteiro;
        this.paraChoqueTraseiro = paraChoqueTraseiro;
        this.placasCaminhao = placasCaminhao;
        this.cintoSeguranca = cintoSeguranca;
        this.pedais = pedais;
        this.aberturaPortas = aberturaPortas;
    }

    public CheckList() {
    }

    public CheckList(String saidRetorno, String dataC, Editable text, String placa, String motorista, String km, String tracao, String calibragemPneu, String estepe, String freioDianteiro, String freioTraseiro, String balanceamento, String limpezaRadiador, String oleoMotor, String filtroOleo, String paraChoqueDianteiro, String paraChoqueTraseiro, String placasCaminhao, String cintoSeguranca, String pedais, String aberturaPortas) {
    }

    public CheckList(String toString, String toString1, Editable text, String toString2, String toString3, String toString4, String toString5) {
    }

    public CheckList(int checkedRadioButtonId, String toString1, Editable text, String toString2, String toString3, String toString4, String toString5) {
    }

    public CheckList(int checkedRadioButtonId, String toString, Editable text, String toString1, String toString2, String toString3, String toString4, int checkedRadioButtonId1, int checkedRadioButtonId2, int checkedRadioButtonId3, int checkedRadioButtonId4, int checkedRadioButtonId5, int checkedRadioButtonId6, int checkedRadioButtonId7, int checkedRadioButtonId8, int checkedRadioButtonId9, int checkedRadioButtonId10, int checkedRadioButtonId11, int checkedRadioButtonId12, int checkedRadioButtonId13, int checkedRadioButtonId14) {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getSaidaRetorno() {
        return saidaRetorno;
    }

    public void setSaidaRetorno(String saidaRetorno) {
        this.saidaRetorno = saidaRetorno;
    }

    public String getDataC() {
        return dataC;
    }

    public void setDataC(String dataC) {
        this.dataC = dataC;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getTracao() {
        return tracao;
    }

    public void setTracao(String tracao) {
        this.tracao = tracao;
    }

    public String getCalibragemPneu() {
        return calibragemPneu;
    }

    public void setCalibragemPneu(String calibragemPneu) {
        this.calibragemPneu = calibragemPneu;
    }

    public String getEstepe() {
        return estepe;
    }

    public void setEstepe(String estepe) {
        this.estepe = estepe;
    }

    public String getFreioDianteiro() {
        return freioDianteiro;
    }

    public void setFreioDianteiro(String freioDianteiro) {
        this.freioDianteiro = freioDianteiro;
    }

    public String getFreioTraseiro() {
        return freioTraseiro;
    }

    public void setFreioTraseiro(String freioTraseiro) {
        this.freioTraseiro = freioTraseiro;
    }

    public String getBalanceamento() {
        return balanceamento;
    }

    public void setBalanceamento(String balanceamento) {
        this.balanceamento = balanceamento;
    }

    public String getLimpezaRadiador() {
        return limpezaRadiador;
    }

    public void setLimpezaRadiador(String limpezaRadiador) {
        this.limpezaRadiador = limpezaRadiador;
    }

    public String getOleoMotor() {
        return oleoMotor;
    }

    public void setOleoMotor(String oleoMotor) {
        this.oleoMotor = oleoMotor;
    }

    public String getFiltroOleo() {
        return filtroOleo;
    }

    public void setFiltroOleo(String filtroOleo) {
        this.filtroOleo = filtroOleo;
    }

    public String getParaChoqueDianteiro() {
        return paraChoqueDianteiro;
    }

    public void setParaChoqueDianteiro(String paraChoqueDianteiro) {
        this.paraChoqueDianteiro = paraChoqueDianteiro;
    }

    public String getParaChoqueTraseiro() {
        return paraChoqueTraseiro;
    }

    public void setParaChoqueTraseiro(String paraChoqueTraseiro) {
        this.paraChoqueTraseiro = paraChoqueTraseiro;
    }

    public String getPlacasCaminhao() {
        return placasCaminhao;
    }

    public void setPlacasCaminhao(String placasCaminhao) {
        this.placasCaminhao = placasCaminhao;
    }

    public String getCintoSeguranca() {
        return cintoSeguranca;
    }

    public void setCintoSeguranca(String cintoSeguranca) {
        this.cintoSeguranca = cintoSeguranca;
    }

    public String getPedais() {
        return pedais;
    }

    public void setPedais(String pedais) {
        this.pedais = pedais;
    }

    public String getAberturaPortas() {
        return aberturaPortas;
    }

    public void setAberturaPortas(String aberturaPortas) {
        this.aberturaPortas = aberturaPortas;
    }

}
